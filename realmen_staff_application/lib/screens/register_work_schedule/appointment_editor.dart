import 'package:dropdown_button2/dropdown_button2.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';
import 'package:realmen_staff_application/service/change_notifier_provider/change_notifier_provider_service.dart';
import 'package:sizer/sizer.dart';
import 'package:syncfusion_flutter_calendar/calendar.dart';

class AppointmentEditorRWS extends StatefulWidget {
  const AppointmentEditorRWS(this.selectedAppointment, this.targetElement,
      this.selectedDate, this._dataSource, this._specialTimeRegions,
      [this.selectedResource]);

  /// Selected appointment
  final Appointment? selectedAppointment;

  /// Calendar element
  final CalendarElement targetElement;

  /// Seelcted date value
  final DateTime selectedDate;

  /// Selected calendar resource
  final CalendarResource? selectedResource;

  /// Holds the events value
  final CalendarDataSource _dataSource;

  final List<TimeRegion> _specialTimeRegions;

  @override
  State<AppointmentEditorRWS> createState() => _AppointmentEditorRWSState();
}

class _AppointmentEditorRWSState extends State<AppointmentEditorRWS> {
  String _subject = '';
  late DateTime _startDate;
  late DateTime _endDate;

  @override
  void initState() {
    _updateAppointmentProperties();
    getDate();
    repeatController = repeatOption.first.toString();
    super.initState();
  }

  @override
  void didUpdateWidget(AppointmentEditorRWS oldWidget) {
    _updateAppointmentProperties();
    super.didUpdateWidget(oldWidget);
  }

  void _updateAppointmentProperties() {
    // kết thúc = không bao giờ
    _endRule = _EndRule.never;

    // lặp lại mỗi = 1 Ngày / Tuần / Tháng
    _count = 1;

    _startDate = widget.selectedAppointment!.startTime;
    _endDate = widget.selectedAppointment!.endTime;
    _selectedDate = _startDate.add(const Duration(days: 30));
    _firstDate = _startDate;
    colorWorkShift = widget.selectedAppointment!.color;
    _subject = widget.selectedAppointment!.subject == '(No title)'
        ? ''
        : widget.selectedAppointment!.subject;
    if (_subject == "CA SÁNG") {
      isMorningSelected = true;
      isNightSelected = false;
    } else if (_subject == "CA TỐI") {
      isMorningSelected = false;
      isNightSelected = true;
    }

    _recurrenceProperties =
        widget.selectedAppointment!.recurrenceRule != null &&
                widget.selectedAppointment!.recurrenceRule!.isNotEmpty
            ? SfCalendar.parseRRule(
                widget.selectedAppointment!.recurrenceRule!, _startDate)
            : null;
    if (_recurrenceProperties == null) {
      _rule = _SelectRule.doesNotRepeat;
    } else {
      _updateMobileRecurrenceProperties();
    }
  }

  @override
  Widget build(BuildContext context) {
    // return AlertDialog(
    //   contentPadding: EdgeInsets.all(0),
    //   content: SizedBox(
    //     width: double.maxFinite,
    //     height: 340,
    //     child: Column(
    //       children: [
    //         Container(
    //           color: colorWorkShift,
    //           padding: const EdgeInsets.only(left: 7),
    //           child: Center(
    //             child: Stack(
    //               children: [
    //                 SizedBox(
    //                   height: 50,
    //                   child: IconButton(
    //                     alignment: Alignment.centerLeft,
    //                     icon: Icon(
    //                       Icons.keyboard_arrow_left,
    //                       color: isMorningSelected || isNightSelected
    //                           ? Colors.white
    //                           : Colors.black,
    //                     ),
    //                     onPressed: () {
    //                       Get.back();
    //                     },
    //                   ),
    //                 ),
    //                 SizedBox(
    //                   height: 50,
    //                   child: Center(
    //                     child: Text(
    //                       "CHỌN CA LÀM".toUpperCase(),
    //                       style: TextStyle(
    //                         color: isMorningSelected || isNightSelected
    //                             ? Colors.white
    //                             : Colors.black,
    //                         fontWeight: FontWeight.w700,
    //                         fontSize: 24,
    //                       ),
    //                     ),
    //                   ),
    //                 ),
    //               ],
    //             ),
    //           ),
    //         ),
    //         // Combo
    //         const SizedBox(
    //           height: 20,
    //         ),
    //         Stack(
    //           children: <Widget>[
    //             _getAppointmentEditor(context, Colors.white, Colors.black87)
    //           ],
    //         ),
    //       ],
    //     ),
    //   ),
    // );

    return Scaffold(
      body: SafeArea(
          child: SingleChildScrollView(
        child: Column(
          children: [
            Container(
              color: colorWorkShift,
              padding: const EdgeInsets.only(left: 7),
              child: Center(
                child: Stack(
                  children: [
                    SizedBox(
                      height: 50,
                      child: IconButton(
                        alignment: Alignment.centerLeft,
                        icon: Icon(
                          Icons.keyboard_arrow_left,
                          color: isMorningSelected || isNightSelected
                              ? Colors.white
                              : Colors.black,
                        ),
                        onPressed: () {
                          Get.back();
                        },
                      ),
                    ),
                    SizedBox(
                      height: 50,
                      child: Center(
                        child: Text(
                          "CHỌN CA LÀM".toUpperCase(),
                          style: TextStyle(
                            color: isMorningSelected || isNightSelected
                                ? Colors.white
                                : Colors.black,
                            fontWeight: FontWeight.w700,
                            fontSize: 24,
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
            // Combo
            const SizedBox(
              height: 20,
            ),
            Stack(
              children: <Widget>[
                _getAppointmentEditor(context, Colors.white, Colors.black87)
              ],
            ),
          ],
        ),
      )),
    );
  }

  save() {
    if (widget.selectedAppointment != null) {
      if (workShift == null) {
        Get.back();
      } else if (widget.selectedAppointment!.subject == '(No title)') {
        final Appointment newAppointment = Appointment(
          startTime: _startDate,
          endTime: _endDate,
          color: colorWorkShift,
          subject: workShift!.name!,
          recurrenceRule: _recurrenceProperties == null
              ? null
              : SfCalendar.generateRRule(
                  _recurrenceProperties!, _startDate, _endDate),
        );
        final apointment = newAppointment;
        final datasource = widget._dataSource.appointments;
        datasource!.add(apointment);
        // widget._dataSource.appointments!.add(newAppointment);

        widget._dataSource.notifyListeners(
            CalendarDataSourceAction.add, <Appointment>[newAppointment]);
        if (workShift!.id! == 1) {
          final DateTime startTimeRegion = DateTime(
              _startDate.year, _startDate.month, _startDate.day, 15, 0, 0);
          final DateTime endTimeRegion =
              DateTime(_endDate.year, _endDate.month, _endDate.day, 23, 0, 0);
          if (widget._specialTimeRegions.any((region) =>
                  region.startTime.isAtSameMomentAs(startTimeRegion) &&
                  region.endTime.isAtSameMomentAs(endTimeRegion)) ==
              false) {
            widget._specialTimeRegions.add(TimeRegion(
              startTime: startTimeRegion,
              endTime: endTimeRegion,
              enablePointerInteraction: false,
            ));
          }
        } else if (workShift!.id! == 2) {
          final DateTime startTimeRegion = DateTime(
              _startDate.year, _startDate.month, _startDate.day, 7, 0, 0);
          final DateTime endTimeRegion =
              DateTime(_endDate.year, _endDate.month, _endDate.day, 15, 0, 0);
          if (widget._specialTimeRegions.any((region) =>
                  region.startTime.isAtSameMomentAs(startTimeRegion) &&
                  region.endTime.isAtSameMomentAs(endTimeRegion)) ==
              false) {
            widget._specialTimeRegions.add(TimeRegion(
              startTime: startTimeRegion,
              endTime: endTimeRegion,
              enablePointerInteraction: false,
            ));
          }
        }

        Future.delayed(const Duration(milliseconds: 200), () {
          // When task is over, close the dialog
          Navigator.pop(context);
        });
      } else {
        if (widget.selectedAppointment != null) {
          if (widget._dataSource.appointments!
              .contains(widget.selectedAppointment)) {
            widget._dataSource.appointments!.removeAt(widget
                ._dataSource.appointments!
                .indexOf(widget.selectedAppointment));
            widget._dataSource.notifyListeners(CalendarDataSourceAction.remove,
                <Appointment>[widget.selectedAppointment!]);
            ChangeNotifierServices changeNotifierServices =
                ChangeNotifierServices();

            changeNotifierServices
                .removeSpecialTimeRegion(widget.selectedAppointment!);
          }
        }
        if (workShift == null) {
          if (widget.selectedAppointment!.subject == "CA SÁNG") {
            workShift = workshifts[0];
          } else if (widget.selectedAppointment!.subject == "CA TỐI") {
            workShift = workshifts[1];
          }
        }
        final Appointment newAppointment = Appointment(
          startTime: _startDate,
          endTime: _endDate,
          color: colorWorkShift,
          subject: workShift!.name!,
          recurrenceRule: _recurrenceProperties == null
              ? null
              : SfCalendar.generateRRule(
                  _recurrenceProperties!, _startDate, _endDate),
        );
        final apointment = newAppointment;
        final datasource = widget._dataSource.appointments;
        datasource!.add(apointment);
        // widget._dataSource.appointments!.add(newAppointment);

        widget._dataSource.notifyListeners(
            CalendarDataSourceAction.add, <Appointment>[newAppointment]);

        if (workShift!.id! == 1) {
          final DateTime startTimeRegion = DateTime(
              _startDate.year, _startDate.month, _startDate.day, 15, 0, 0);
          final DateTime endTimeRegion =
              DateTime(_endDate.year, _endDate.month, _endDate.day, 23, 0, 0);
          if (widget._specialTimeRegions.any((region) =>
                  region.startTime.isAtSameMomentAs(startTimeRegion) &&
                  region.endTime.isAtSameMomentAs(endTimeRegion)) ==
              false) {
            widget._specialTimeRegions.add(TimeRegion(
              startTime: startTimeRegion,
              endTime: endTimeRegion,
              enablePointerInteraction: false,
            ));
          }
        } else if (workShift!.id! == 2) {
          final DateTime startTimeRegion = DateTime(
              _startDate.year, _startDate.month, _startDate.day, 7, 0, 0);
          final DateTime endTimeRegion =
              DateTime(_endDate.year, _endDate.month, _endDate.day, 15, 0, 0);
          if (widget._specialTimeRegions.any((region) =>
                  region.startTime.isAtSameMomentAs(startTimeRegion) &&
                  region.endTime.isAtSameMomentAs(endTimeRegion)) ==
              false) {
            widget._specialTimeRegions.add(TimeRegion(
              startTime: startTimeRegion,
              endTime: endTimeRegion,
              enablePointerInteraction: false,
            ));
          }
        }
        Future.delayed(const Duration(milliseconds: 200), () {
          // When task is over, close the dialog
          Navigator.pop(context);
        });
      }
    }
  }

  Widget _getAppointmentEditor(
      BuildContext context, Color backgroundColor, Color defaultColor) {
    return Container(
      color: backgroundColor,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Center(
            child: Text(
              txtSelectedDate!,
              style: const TextStyle(color: Colors.black, fontSize: 24),
            ),
          ),
          const SizedBox(
            height: 20,
          ),
          Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              Container(
                width: 150,
                decoration: BoxDecoration(
                    border: Border.all(width: 1, style: BorderStyle.solid),
                    borderRadius: BorderRadius.circular(10),
                    color: isMorningSelected
                        ? Colors.deepOrange.shade400
                        : Colors.transparent),
                child: TextButton(
                  onPressed: () {
                    _onSelected(1);
                  },
                  child: SizedBox(
                    height: 60,
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          "CA SÁNG",
                          style: TextStyle(
                              color: isMorningSelected
                                  ? Colors.white
                                  : Colors.black,
                              fontSize: 23),
                        ),
                        Text(
                          "( 7:00 - 15:00 )",
                          style: TextStyle(
                              color: isMorningSelected
                                  ? Colors.white
                                  : Colors.black,
                              fontSize: 18),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
              Container(
                width: 150,
                decoration: BoxDecoration(
                    border: Border.all(width: 1, style: BorderStyle.solid),
                    borderRadius: BorderRadius.circular(10),
                    color: isNightSelected
                        ? Colors.deepPurple.shade400
                        : Colors.transparent),
                child: TextButton(
                  onPressed: () {
                    _onSelected(2);
                  },
                  child: SizedBox(
                    height: 60,
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          "CA TỐI",
                          style: TextStyle(
                              color:
                                  isNightSelected ? Colors.white : Colors.black,
                              fontSize: 23),
                        ),
                        Text(
                          "( 15:00 - 23:00 )",
                          style: TextStyle(
                              color:
                                  isNightSelected ? Colors.white : Colors.black,
                              fontSize: 18),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            ],
          ),
          const SizedBox(
            height: 30,
          ),
          const Padding(
            padding: EdgeInsets.symmetric(
              horizontal: 10,
            ),
            child: Divider(
              color: Colors.grey,
              height: 2,
              thickness: 1,
            ),
          ),
          const SizedBox(
            height: 15,
          ),
          Container(
            margin: const EdgeInsets.symmetric(horizontal: 30),
            height: 30,
            child: Text(
              "Lặp lại:".toUpperCase(),
              style: const TextStyle(
                  color: Colors.black,
                  fontSize: 21,
                  fontWeight: FontWeight.w500),
            ),
          ),
          const SizedBox(
            height: 10,
          ),
          ClipRRect(
            borderRadius: BorderRadius.circular(5),
            child: Container(
              margin: const EdgeInsets.symmetric(horizontal: 30),
              padding: const EdgeInsets.only(left: 10),
              decoration: BoxDecoration(
                border: Border.all(
                  color: Colors.grey,
                  width: 1,
                  style: BorderStyle.solid,
                ),
                borderRadius: BorderRadius.circular(5),
                color: Colors.white,
              ),
              child: DropdownButtonHideUnderline(
                  child: DropdownButton2(
                isExpanded: true,
                alignment: Alignment.center,
                value: repeatController,
                items: repeatOption
                    .map(
                      (item) => DropdownMenuItem(
                        value: item,
                        child: Container(
                          padding:
                              const EdgeInsets.only(top: 5, bottom: 5, left: 0),
                          child: Text(
                            item.toString(),
                            style: const TextStyle(
                                color: Colors.black, fontSize: 20),
                          ),
                        ),
                      ),
                    )
                    .toList(),
                onChanged: (value) {
                  setState(() {
                    repeatController = value.toString();
                    _updateRecurrenceProperties(repeatController!);
                  });
                },
                dropdownStyleData: DropdownStyleData(
                  maxHeight: 200,
                  width: 325,
                  padding: const EdgeInsets.all(0),
                  decoration: BoxDecoration(
                    // borderRadius: BorderRadius.circular(14),
                    color: Colors.grey.shade200,
                  ),
                  offset: const Offset(-35, -6),
                  scrollbarTheme: ScrollbarThemeData(
                    // radius: const Radius.circular(40),
                    // thickness: MaterialStateProperty.all(6),
                    thumbVisibility: MaterialStateProperty.all(true),
                  ),
                ),
                menuItemStyleData: const MenuItemStyleData(
                  height: 40,
                  padding: EdgeInsets.only(left: 35, right: 24),
                ),
                buttonStyleData: const ButtonStyleData(
                  padding: EdgeInsets.all(0),
                ),
              )),
            ),
          ),
          repeatController != 'Không lặp lại'
              ? Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const SizedBox(
                      height: 30,
                    ),
                    const Padding(
                      padding: EdgeInsets.symmetric(
                        horizontal: 10,
                      ),
                      child: Divider(
                        color: Colors.grey,
                        height: 2,
                        thickness: 1,
                      ),
                    ),
                    const SizedBox(
                      height: 15,
                    ),
                    Container(
                      margin: const EdgeInsets.symmetric(horizontal: 30),
                      height: 30,
                      child: Row(
                        children: [
                          Text(
                            "Lặp lại mỗi:".toUpperCase(),
                            style: const TextStyle(
                                color: Colors.black,
                                fontSize: 21,
                                fontWeight: FontWeight.w500),
                          ),
                          const SizedBox(
                            width: 10,
                          ),
                          ClipRRect(
                            borderRadius: BorderRadius.circular(7),
                            child: Container(
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(7),
                                border: Border.all(
                                    color: const Color(0xffC4C4C4),
                                    width: 1,
                                    style: BorderStyle.solid),
                              ),
                              child: Row(
                                children: [
                                  SizedBox(
                                    width: 15.w,
                                    // height: 40,
                                    child: TextField(
                                      controller: repeatEveryController,
                                      keyboardType: TextInputType.number,
                                      inputFormatters: <TextInputFormatter>[
                                        FilteringTextInputFormatter.allow(
                                            RegExp(r'[0-9]')),
                                      ],
                                      cursorColor: Colors.black,
                                      cursorWidth: 1,
                                      style: const TextStyle(
                                          height: 1.17,
                                          fontSize: 20,
                                          color: Colors.black),
                                      decoration: const InputDecoration(
                                        enabledBorder: OutlineInputBorder(
                                          borderSide: BorderSide(
                                              color: Colors.transparent),
                                        ),
                                        focusedBorder: OutlineInputBorder(
                                          borderSide: BorderSide(
                                              color: Colors.transparent),
                                        ),
                                        contentPadding: EdgeInsets.only(
                                            // top: 10,
                                            // bottom: 20,
                                            left: 15,
                                            right: 15),
                                      ),
                                    ),
                                  ),
                                  Container(
                                    decoration: BoxDecoration(
                                      border: Border.symmetric(
                                        vertical: BorderSide(
                                            color: Color(0xffC4C4C4),
                                            style: BorderStyle.solid,
                                            width: 1),
                                      ),
                                    ),
                                    child: IconButton(
                                      icon: Icon(
                                        Icons.add,
                                        color: Colors.black,
                                      ),
                                      padding: EdgeInsets.symmetric(
                                          vertical: 0, horizontal: 10.0),
                                      iconSize: 32.0,
                                      color: Theme.of(context).primaryColor,
                                      onPressed: () {
                                        int count = int.parse(
                                            repeatEveryController.text
                                                .toString());
                                        count = count + 1;
                                        repeatEveryController =
                                            TextEditingController(
                                                text: count.toString());
                                        setState(() {
                                          repeatEveryController;
                                          _recurrenceProperties!.interval =
                                              int.parse(repeatEveryController
                                                  .text
                                                  .toString());
                                        });
                                      },
                                    ),
                                  ),
                                  IconButton(
                                    icon: Icon(
                                      Icons.remove,
                                      color: Colors.black,
                                    ),
                                    padding: EdgeInsets.symmetric(
                                        vertical: 0, horizontal: 10.0),
                                    iconSize: 32.0,
                                    color: Theme.of(context).primaryColor,
                                    onPressed: () {
                                      int count = int.parse(
                                          repeatEveryController.text
                                              .toString());
                                      if (count > 1) {
                                        count = count - 1;
                                      } else {
                                        count = 1;
                                      }

                                      repeatEveryController =
                                          TextEditingController(
                                              text: count.toString());

                                      setState(() {
                                        repeatEveryController;
                                      });
                                    },
                                  ),
                                ],
                              ),
                            ),
                          ),
                          const SizedBox(
                            width: 5,
                          ),
                          Text(
                            repeatController == 'Hằng ngày'
                                ? "Ngày"
                                : repeatController == 'Hằng tuần'
                                    ? "Tuần"
                                    : "Tháng",
                            style: TextStyle(color: Colors.black, fontSize: 18),
                          ),
                        ],
                      ),
                    ),
                  ],
                )
              : Container(),
          repeatController == 'Hằng tháng'
              ? Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const SizedBox(
                      height: 30,
                    ),
                    const Padding(
                      padding: EdgeInsets.symmetric(
                        horizontal: 10,
                      ),
                      child: Divider(
                        color: Colors.grey,
                        height: 2,
                        thickness: 1,
                      ),
                    ),
                    const SizedBox(
                      height: 15,
                    ),
                    Container(
                      margin: const EdgeInsets.symmetric(horizontal: 30),
                      height: 30,
                      child: Text(
                        "lặp lại tháng theo:".toUpperCase(),
                        style: const TextStyle(
                            color: Colors.black,
                            fontSize: 21,
                            fontWeight: FontWeight.w500),
                      ),
                    ),
                    const SizedBox(
                      height: 10,
                    ),
                    ClipRRect(
                      borderRadius: BorderRadius.circular(5),
                      child: Container(
                        margin: const EdgeInsets.symmetric(horizontal: 30),
                        padding: const EdgeInsets.only(left: 10),
                        decoration: BoxDecoration(
                          border: Border.all(
                            color: Colors.grey,
                            width: 1,
                            style: BorderStyle.solid,
                          ),
                          borderRadius: BorderRadius.circular(5),
                          color: Colors.white,
                        ),
                        child: DropdownButtonHideUnderline(
                            child: DropdownButton2(
                          isExpanded: true,
                          alignment: Alignment.center,
                          value: repeatMonthlyController,
                          items: repeatMonthlyOption
                              .map(
                                (item) => DropdownMenuItem(
                                  value: item,
                                  child: Container(
                                    padding: const EdgeInsets.only(
                                        top: 5, bottom: 5, left: 0),
                                    child: Text(
                                      item.toString(),
                                      style: const TextStyle(
                                          color: Colors.black, fontSize: 20),
                                    ),
                                  ),
                                ),
                              )
                              .toList(),
                          onChanged: (value) {
                            setState(() {
                              repeatMonthlyController = value.toString();
                            });
                          },
                          dropdownStyleData: DropdownStyleData(
                            maxHeight: 200,
                            width: 325,
                            padding: const EdgeInsets.all(0),
                            decoration: BoxDecoration(
                              // borderRadius: BorderRadius.circular(14),
                              color: Colors.grey.shade200,
                            ),
                            offset: const Offset(-35, -6),
                            scrollbarTheme: ScrollbarThemeData(
                              // radius: const Radius.circular(40),
                              // thickness: MaterialStateProperty.all(6),
                              thumbVisibility: MaterialStateProperty.all(true),
                            ),
                          ),
                          menuItemStyleData: const MenuItemStyleData(
                            height: 40,
                            padding: EdgeInsets.only(left: 35, right: 24),
                          ),
                          buttonStyleData: const ButtonStyleData(
                            padding: EdgeInsets.all(0),
                          ),
                        )),
                      ),
                    ),
                    repeatMonthlyController == "Các thứ trong tuần hằng tháng"
                        ? Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              const SizedBox(
                                height: 15,
                              ),
                              Container(
                                margin:
                                    const EdgeInsets.symmetric(horizontal: 30),
                                height: 30,
                                child: Text(
                                  "các thứ trong tuần:".toUpperCase(),
                                  style: const TextStyle(
                                      color: Colors.black,
                                      fontSize: 21,
                                      fontWeight: FontWeight.w500),
                                ),
                              ),
                              const SizedBox(
                                height: 10,
                              ),
                              Container(
                                margin:
                                    const EdgeInsets.symmetric(horizontal: 30),
                                height: 40,
                                width: 80.w,
                                child: ListView.builder(
                                  scrollDirection: Axis.horizontal,
                                  padding: EdgeInsets.only(left: 0),
                                  itemCount: _weekDay.length,
                                  itemBuilder: (context, index) {
                                    final isSelected = isSeletedDayofWeek
                                        .contains(_weekDay[index]);
                                    return ElevatedButton(
                                      onPressed: () {
                                        setState(() {
                                          if (isSeletedDayofWeek
                                              .contains(_weekDay[index])) {
                                            isSeletedDayofWeek.removeWhere(
                                                (element) =>
                                                    element == _weekDay[index]);
                                          } else {
                                            isSeletedDayofWeek
                                                .add(_weekDay[index]);
                                          }
                                        });
                                      },
                                      style: ElevatedButton.styleFrom(
                                        minimumSize: const Size(6, 6),
                                        backgroundColor: isSelected
                                            ? Colors.blueAccent
                                            : Colors.white,
                                        foregroundColor: isSelected
                                            ? Colors.white
                                            : Colors.blueAccent,
                                        shape: const CircleBorder(),
                                        padding: const EdgeInsets.all(10),
                                      ),
                                      child: Text(
                                        _weekDay[index]['key'],
                                        style: TextStyle(fontSize: 16),
                                      ),
                                    );
                                  },
                                ),
                              ),
                            ],
                          )
                        : Container(),
                  ],
                )
              : Container(),
          repeatController == 'Hằng tuần'
              ? Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const SizedBox(
                      height: 30,
                    ),
                    const Padding(
                      padding: EdgeInsets.symmetric(
                        horizontal: 10,
                      ),
                      child: Divider(
                        color: Colors.grey,
                        height: 2,
                        thickness: 1,
                      ),
                    ),
                    const SizedBox(
                      height: 15,
                    ),
                    Container(
                      margin: const EdgeInsets.symmetric(horizontal: 30),
                      height: 30,
                      child: Text(
                        "lặp lại vào các thứ trong tuần:".toUpperCase(),
                        style: const TextStyle(
                            color: Colors.black,
                            fontSize: 21,
                            fontWeight: FontWeight.w500),
                      ),
                    ),
                    const SizedBox(
                      height: 10,
                    ),
                    Container(
                      margin: const EdgeInsets.symmetric(horizontal: 30),
                      height: 40,
                      width: 80.w,
                      child: ListView.builder(
                        scrollDirection: Axis.horizontal,
                        padding: EdgeInsets.only(left: 0),
                        itemCount: _weekDay.length,
                        itemBuilder: (context, index) {
                          final isSelected =
                              isSeletedDayofWeek.contains(_weekDay[index]);
                          return ElevatedButton(
                            onPressed: () {
                              setState(() {
                                if (isSeletedDayofWeek
                                    .contains(_weekDay[index])) {
                                  isSeletedDayofWeek.removeWhere(
                                      (element) => element == _weekDay[index]);
                                } else {
                                  isSeletedDayofWeek.add(_weekDay[index]);
                                }
                              });
                            },
                            style: ElevatedButton.styleFrom(
                              minimumSize: const Size(6, 6),
                              backgroundColor:
                                  isSelected ? Colors.blueAccent : Colors.white,
                              foregroundColor:
                                  isSelected ? Colors.white : Colors.blueAccent,
                              shape: const CircleBorder(),
                              padding: const EdgeInsets.all(10),
                            ),
                            child: Text(
                              _weekDay[index]['key'],
                              style: TextStyle(fontSize: 16),
                            ),
                          );
                        },
                      ),
                    ),
                  ],
                )
              : Container(),
          repeatController != 'Không lặp lại'
              ? Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const SizedBox(
                      height: 30,
                    ),
                    const Padding(
                      padding: EdgeInsets.symmetric(
                        horizontal: 10,
                      ),
                      child: Divider(
                        color: Colors.grey,
                        height: 2,
                        thickness: 1,
                      ),
                    ),
                    const SizedBox(
                      height: 15,
                    ),
                    Container(
                      margin: const EdgeInsets.symmetric(horizontal: 30),
                      height: 30,
                      child: Text(
                        "kết thúc:".toUpperCase(),
                        style: const TextStyle(
                            color: Colors.black,
                            fontSize: 21,
                            fontWeight: FontWeight.w500),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 20),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          RadioListTile<_EndRule>(
                            contentPadding: const EdgeInsets.only(left: 7),
                            title: const Text('Không kết thúc'),
                            value: _EndRule.never,
                            groupValue: _endRule,
                            activeColor: Colors.blueAccent,
                            onChanged: (_EndRule? value) {
                              setState(() {
                                _endRule = _EndRule.never;
                                _rangeNoEndDate();
                              });
                            },
                          ),
                          const Divider(
                            indent: 50,
                            height: 1.0,
                            thickness: 1,
                          ),
                          RadioListTile<_EndRule>(
                            contentPadding: const EdgeInsets.only(left: 7),
                            title: Row(
                              children: <Widget>[
                                const Text('Cho đến'),
                                Container(
                                  margin: const EdgeInsets.only(left: 5),
                                  width: 110,
                                  height: 40,
                                  decoration: BoxDecoration(
                                    color: Colors.grey.withOpacity(0.1),
                                    borderRadius: BorderRadius.circular(3),
                                  ),
                                  child: ButtonTheme(
                                    minWidth: 30.0,
                                    child: MaterialButton(
                                      elevation: 0,
                                      focusElevation: 0,
                                      highlightElevation: 0,
                                      disabledElevation: 0,
                                      hoverElevation: 0,
                                      onPressed: () async {
                                        final DateTime? pickedDate =
                                            await showDatePicker(
                                                context: context,
                                                initialDate: _selectedDate,
                                                firstDate: _startDate
                                                        .isBefore(_firstDate)
                                                    ? _startDate
                                                    : _firstDate,
                                                currentDate: _selectedDate,
                                                lastDate: DateTime(2050),
                                                builder: (BuildContext context,
                                                    Widget? child) {
                                                  return Theme(
                                                    data: ThemeData.light()
                                                        .copyWith(
                                                      primaryColor: Colors
                                                          .blueAccent, // Màu chính của DatePicker
                                                      // Màu phụ của DatePicker
                                                      colorScheme:
                                                          ColorScheme.light(
                                                              primary: Colors
                                                                  .blueAccent), // Bổ sung cho màu chính
                                                      buttonTheme: ButtonThemeData(
                                                          textTheme: ButtonTextTheme
                                                              .primary), // Thiết lập cho nút
                                                    ),
                                                    child: child!,
                                                  );
                                                });
                                        if (pickedDate == null) {
                                          return;
                                        }
                                        setState(() {
                                          _endRule = _EndRule.endDate;
                                          _recurrenceProperties!
                                                  .recurrenceRange =
                                              RecurrenceRange.endDate;
                                          _selectedDate = DateTime(
                                              pickedDate.year,
                                              pickedDate.month,
                                              pickedDate.day);
                                          _recurrenceProperties!.endDate =
                                              _selectedDate;
                                        });
                                      },
                                      shape: const CircleBorder(),
                                      child: Text(
                                        DateFormat('MM/dd/yyyy')
                                            .format(_selectedDate),
                                        style: TextStyle(
                                            fontSize: 13,
                                            color: Colors.black,
                                            fontWeight: FontWeight.w400),
                                      ),
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            value: _EndRule.endDate,
                            groupValue: _endRule,
                            activeColor: Colors.blueAccent,
                            onChanged: (_EndRule? value) {
                              setState(() {
                                _endRule = value;
                                // _rangeEndDate();
                              });
                            },
                          ),
                          const Divider(
                            indent: 50,
                            height: 1.0,
                            thickness: 1,
                          ),
                          SizedBox(
                            height: 40,
                            child: RadioListTile<_EndRule>(
                              contentPadding: const EdgeInsets.only(left: 7),
                              title: Row(
                                children: <Widget>[
                                  const Text('Sau'),
                                  Container(
                                    height: 40,
                                    width: 60,
                                    padding: const EdgeInsets.only(
                                        left: 5, bottom: 10),
                                    margin: const EdgeInsets.only(left: 5),
                                    alignment: Alignment.topCenter,
                                    decoration: BoxDecoration(
                                      color: Colors.grey.withOpacity(0.1),
                                      borderRadius: BorderRadius.circular(3),
                                    ),
                                    child: TextField(
                                      readOnly: _endRule != _EndRule.count,
                                      controller:
                                          TextEditingController.fromValue(
                                              TextEditingValue(
                                                  text: _count.toString(),
                                                  selection:
                                                      TextSelection.collapsed(
                                                          offset: _count
                                                              .toString()
                                                              .length))),
                                      // cursorColor: widget.model.backgroundColor,
                                      onTap: () {
                                        setState(() {
                                          _endRule = _EndRule.count;
                                        });
                                      },
                                      onChanged: (String value) async {
                                        if (value != null && value.isNotEmpty) {
                                          _count = int.parse(value);
                                          if (_count == 0) {
                                            _count = 1;
                                          } else if (_count! >= 999) {
                                            setState(() {
                                              _count = 999;
                                            });
                                          }
                                        } else if (value.isEmpty ||
                                            value == null) {
                                          _count = 1;
                                        }
                                        _endRule = _EndRule.count;
                                        _recurrenceProperties!.recurrenceRange =
                                            RecurrenceRange.count;
                                        _recurrenceProperties!.recurrenceCount =
                                            _count!;
                                      },
                                      keyboardType: TextInputType.number,
                                      // ignore: always_specify_types
                                      inputFormatters: [
                                        FilteringTextInputFormatter.digitsOnly
                                      ],
                                      style: TextStyle(
                                          fontSize: 13,
                                          color: Colors.black,
                                          fontWeight: FontWeight.w400),
                                      textAlign: TextAlign.center,
                                      decoration: const InputDecoration(
                                          border: InputBorder.none),
                                    ),
                                  ),
                                  Container(
                                    width: 10,
                                  ),
                                  const Text('lần'),
                                ],
                              ),
                              value: _EndRule.count,
                              groupValue: _endRule,
                              activeColor: Colors.blueAccent,
                              onChanged: (_EndRule? value) {
                                setState(() {
                                  _endRule = value;
                                  _recurrenceProperties!.recurrenceRange =
                                      RecurrenceRange.count;
                                  _recurrenceProperties!.recurrenceCount =
                                      _count!;
                                });
                              },
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                )
              : Container(),
          const SizedBox(
            height: 30,
          ),
          Container(
            height: 50,
            margin: const EdgeInsets.symmetric(horizontal: 10),
            decoration: BoxDecoration(
                border: Border.all(
                    width: 1, color: Colors.black54, style: BorderStyle.solid),
                borderRadius: BorderRadius.circular(10),
                color: colorWorkShift),
            child: TextButton(
              onPressed: save,
              child: Center(
                child: Text(
                  "xác nhận".toUpperCase(),
                  style: TextStyle(
                    color: isMorningSelected || isNightSelected
                        ? Colors.white
                        : Colors.black,
                    fontWeight: FontWeight.w700,
                    fontSize: 29,
                  ),
                ),
              ),
            ),
          ),
          const SizedBox(
            height: 30,
          ),
        ],
      ),
    );
  }

  bool isMorningSelected = false;
  bool isNightSelected = false;
  WorkShift? workShift;
  Color colorWorkShift = Colors.grey;
  void _onSelected(int id) {
    if (id == 1) {
      setState(() {
        isMorningSelected = true;
        isNightSelected = false;
        workShift = workshifts[0];
        colorWorkShift = Colors.deepOrange.shade400;
        _startDate = DateTime(
            _startDate.year, _startDate.month, _startDate.day, 7, 0, 0);
        _endDate =
            DateTime(_endDate.year, _endDate.month, _endDate.day, 15, 0, 0);
      });
    } else if (id == 2) {
      setState(() {
        isMorningSelected = false;
        isNightSelected = true;
        workShift = workshifts[1];
        colorWorkShift = Colors.deepPurple.shade400;
        _startDate = DateTime(
            _startDate.year, _startDate.month, _startDate.day, 15, 0, 0);
        _endDate =
            DateTime(_endDate.year, _endDate.month, _endDate.day, 23, 0, 0);
      });
    }
  }

  final List<WorkShift> workshifts = [
    WorkShift(
        id: 1,
        name: "CA SÁNG",
        timeSlot: "( 7:00 - 15:00 )",
        startTime: 7,
        endTime: 15),
    WorkShift(
        id: 2,
        name: "CA TỐI",
        timeSlot: "( 15:00 - 23:00 )",
        startTime: 15,
        endTime: 23),
  ];
  String? txtSelectedDate;
  getDate() {
    DateTime selectedDate = widget.selectedDate;
    String day = DateFormat('EEEE').format(selectedDate);
    day = formatDay(day);
    setState(() {
      txtSelectedDate =
          "$day, ${DateFormat('dd/MM/yyyy').format(selectedDate)}";
    });
  }

  String formatDay(String day) {
    return dayNames[day.toLowerCase()] ?? day;
  }

  final Map<String, String> dayNames = {
    'monday': 'Thứ hai',
    'tuesday': 'Thứ ba',
    'wednesday': 'Thứ tư',
    'thursday': 'Thứ năm',
    'friday': 'Thứ sáu',
    'saturday': 'Thứ bảy',
    'sunday': 'Chủ nhật'
  };

  //Recurrence lặp lại
  RecurrenceProperties? _recurrenceProperties;
  _SelectRule? _rule = _SelectRule.doesNotRepeat;

  // Reapeat
  String? repeatController;
  TextEditingController repeatEveryController =
      TextEditingController(text: '1');
  List<String> repeatOption = <String>[
    'Không lặp lại',
    'Hằng ngày',
    'Hằng tuần',
    'Hằng tháng',
  ];

  /// Dropdown list items for day of week
  final List<Map<String, dynamic>> _weekDay = [
    {'value': 'Thứ Hai', 'key': '2'},
    {'value': 'Thứ Ba', 'key': '3'},
    {'value': 'Thứ Tư', 'key': '4'},
    {'value': 'Thứ Năm', 'key': '5'},
    {'value': 'Thứ Sáu', 'key': '6'},
    {'value': 'Thứ Bảy', 'key': '7'},
    {'value': 'Chủ Nhật', 'key': 'CN'},
  ];
  List<Map<String, dynamic>> isSeletedDayofWeek = [];
  String? repeatMonthlyController;
  List<String> repeatMonthlyOption = <String>[
    'Ngày này hằng tháng',
    'Các thứ trong tuần hằng tháng',
  ];

  /// Dropdown list items for end range
  // List<String> _ends = <String>[
  //   'Không kết thúc',
  //   'Cho đến',
  //   'Số lần',
  // ];

  // chọn kiểu kết thúc
  _EndRule? _endRule;

  late DateTime _selectedDate, _firstDate;

  // số lần lặp lại
  int? _count;

  // rule daily week month
  late RecurrenceType _recurrenceType;

  // khoảng lặp lại
  RecurrenceRange? _recurrenceRange;

  // khoảng thời gian
  // == int.parse(repeatEveryController.text.toString())
  late int _interval;

  void _updateMobileRecurrenceProperties() {
    _recurrenceType = _recurrenceProperties!.recurrenceType;
    _recurrenceRange = _recurrenceProperties!.recurrenceRange;
    _interval = _recurrenceProperties!.interval;
    if (_interval == 1 && _recurrenceRange == RecurrenceRange.noEndDate) {
      switch (_recurrenceType) {
        case RecurrenceType.daily:
          _rule = _SelectRule.everyDay;
          break;
        case RecurrenceType.weekly:
          if (_recurrenceProperties!.weekDays.length == 1) {
            _rule = _SelectRule.everyWeek;
          } else {
            _rule = _SelectRule.doesNotRepeat;
          }
          break;
        case RecurrenceType.monthly:
          _rule = _SelectRule.everyMonth;
          break;
      }
    } else {
      _rule = _SelectRule.doesNotRepeat;
    }
  }

  void _rangeNoEndDate() {
    _recurrenceProperties!.recurrenceRange = RecurrenceRange.noEndDate;
  }

  void _rangeEndDate() {
    _recurrenceProperties!.recurrenceRange = RecurrenceRange.endDate;
    _selectedDate = _recurrenceProperties!.endDate ??
        _startDate.add(const Duration(days: 30));
    _recurrenceProperties!.endDate = _selectedDate;
  }

  void _rangeCount() {
    _recurrenceProperties!.recurrenceRange = RecurrenceRange.count;
    _count = _recurrenceProperties!.recurrenceCount == 0
        ? 1
        : _recurrenceProperties!.recurrenceCount;
    _recurrenceProperties!.recurrenceCount = _count!;
  }

  void _updateRecurrenceProperties(String repeatController) {
    if (repeatController.toString() == 'Không lặp lại') {
      _recurrenceProperties = null;
      _rule = _SelectRule.doesNotRepeat;
    } else if (repeatController.toString() == 'Hằng ngày') {
      _recurrenceProperties = RecurrenceProperties(startDate: _startDate);
      _recurrenceProperties!.recurrenceType = RecurrenceType.daily;
      _recurrenceProperties!.interval = 1;
      _recurrenceProperties!.recurrenceRange = RecurrenceRange.noEndDate;
      _rule = _SelectRule.everyDay;
    } else if (repeatController.toString() == 'Hằng tuần') {
      _recurrenceProperties = RecurrenceProperties(startDate: _startDate);
      _recurrenceProperties!.recurrenceType = RecurrenceType.weekly;
      _recurrenceProperties!.interval = 1;
      _recurrenceProperties!.recurrenceRange = RecurrenceRange.noEndDate;
      _recurrenceProperties!.weekDays = _startDate.weekday == 1
          ? <WeekDays>[WeekDays.monday]
          : _startDate.weekday == 2
              ? <WeekDays>[WeekDays.tuesday]
              : _startDate.weekday == 3
                  ? <WeekDays>[WeekDays.wednesday]
                  : _startDate.weekday == 4
                      ? <WeekDays>[WeekDays.thursday]
                      : _startDate.weekday == 5
                          ? <WeekDays>[WeekDays.friday]
                          : _startDate.weekday == 6
                              ? <WeekDays>[WeekDays.saturday]
                              : <WeekDays>[WeekDays.sunday];
      _rule = _SelectRule.everyWeek;
    } else if (repeatController.toString() == 'Hằng tuần') {
      _recurrenceProperties = RecurrenceProperties(startDate: _startDate);
      _recurrenceProperties!.recurrenceType = RecurrenceType.monthly;
      _recurrenceProperties!.interval = 1;
      _recurrenceProperties!.recurrenceRange = RecurrenceRange.noEndDate;
      _recurrenceProperties!.dayOfMonth =
          widget.selectedAppointment!.startTime.day;
      _rule = _SelectRule.everyMonth;
    }
  }
}

enum _EndRule { never, endDate, count }

enum _SelectRule {
  doesNotRepeat,
  everyDay,
  everyWeek,
  everyMonth,
}

class WorkShift {
  int? id;
  String? name;
  String? timeSlot;
  int? startTime;
  int? endTime;
  WorkShift({
    this.id,
    this.name,
    this.timeSlot,
    this.startTime,
    this.endTime,
  });
}
