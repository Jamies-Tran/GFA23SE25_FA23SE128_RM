// ignore_for_file: avoid_print, must_be_immutable

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';
import 'package:realmen_customer_application/models/account/account_info_model.dart';
import 'package:realmen_customer_application/models/booking/booking_model.dart';
import 'package:realmen_customer_application/models/branch/branch_model.dart';
import 'package:realmen_customer_application/screens/booking/booking_haircut_temporary.dart';
import 'package:realmen_customer_application/screens/booking/components/choose_branch/branch_booking_choose.dart';
import 'package:realmen_customer_application/screens/booking/components/choose_service/service_booking_choose.dart';
import 'package:realmen_customer_application/screens/booking/components/choose_stylist_date_time/stylist_date_time_booking_choose.dart';
import 'package:realmen_customer_application/screens/message/success_screen.dart';
import 'package:sizer/sizer.dart';
import 'package:timeline_tile/timeline_tile.dart';

class BranchOptionBooking extends StatefulWidget {
  BranchOptionBooking(this.callback, {super.key});
  Function? callback;
  @override
  State<BranchOptionBooking> createState() => _BranchOptionBookingState();
}

class _BranchOptionBookingState extends State<BranchOptionBooking>
    with AutomaticKeepAliveClientMixin {
  @override
  Widget build(BuildContext context) {
    super.build(context);
    return SingleChildScrollView(
        child: Column(
      children: [
        // 1 chon chi nhanh
        ChooseBranchBooking(onBranchSelected: updateSelectedBranch),

        // 2
        TimelineTile(
          // false la hien thanh
          isLast: false,
          beforeLineStyle: const LineStyle(color: Colors.black, thickness: 2),

          // icon
          indicatorStyle: IndicatorStyle(
            color: Colors.transparent,
            width: 35,
            height: 40,
            padding: const EdgeInsets.only(top: 4, bottom: 4, right: 5),
            indicator: Image.asset('assets/images/logo-no-text.png'),
            indicatorXY: 0.0,
          ),

          // content
          endChild: selectedBranch.branchId != null
              ? (selectedBranch.branchServiceList != null &&
                      selectedBranch.branchServiceList!.isNotEmpty
                  ? ChooseServiceBooking(
                      onServiceSelected: updateSelectedService,
                      branchServiceList: selectedBranch.branchServiceList!,
                      isUpdateBranch: isUpdateBranch)
                  : Container(
                      height: 150,
                      padding: const EdgeInsets.only(top: 10, right: 15),
                      constraints: const BoxConstraints(minHeight: 120),
                      child: const Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            "2. Chọn dịch vụ ",
                            style: TextStyle(fontSize: 20),
                          ),
                          SizedBox(
                            height: 10,
                          ),
                          Center(
                            child: Text("Chi nhánh hiện chưa có dịch vụ."),
                          ),
                          Center(
                            child: Text(
                                "Quý khách hành vui lòng chọn Chi nhánh khác!"),
                          ),
                        ],
                      ),
                    ))
              : Container(
                  height: 150,
                  padding: const EdgeInsets.only(top: 10, right: 15),
                  constraints: const BoxConstraints(minHeight: 120),
                  child: const Text(
                    "2. Chọn dịch vụ ",
                    style: TextStyle(fontSize: 20),
                  )),
        ),

        // 3
        TimelineTile(
          // false la hien thanh
          isLast: false,
          beforeLineStyle: const LineStyle(color: Colors.black, thickness: 2),

          // icon
          indicatorStyle: IndicatorStyle(
            color: Colors.transparent,
            width: 35,
            height: 40,
            padding: const EdgeInsets.only(top: 4, bottom: 4, right: 5),
            indicator: Image.asset('assets/images/logo-no-text.png'),
            indicatorXY: 0.0,
          ),

          // content
          endChild: selectedBranch.branchId != null &&
                  // selectedService != null &&
                  selectedService.isNotEmpty &&
                  selectedBranch.branchServiceList != null &&
                  selectedBranch.branchServiceList!.isNotEmpty
              ? ChooseStylistAndDateTimeBooking(
                  onDateSelected: updateSelectedDate,
                  onTimeSelected: updateSelectedTime,
                  onStylistSelected: updateSelectedStylist,
                  accountStaffList: selectedBranch.accountStaffList!,
                  selectedService: selectedService,
                  onUpdatePostBooking: updatePostBooking,
                  onUpdateOption: updateOption,
                  openBranch: selectedBranch.open!,
                  closeBranch: selectedBranch.close!,
                  massuerList: massuerList,
                )
              : Container(
                  height: 150,
                  padding: const EdgeInsets.only(top: 10, right: 15),
                  constraints: const BoxConstraints(minHeight: 120),
                  child: const Text(
                    "3. Chọn stylist & ngày, giờ ",
                    style: TextStyle(fontSize: 20),
                  )),
        ),
        // button Đặt Lịch
        selectedBranch.branchId != null && selectedService != []
            ? Column(
                children: [
                  Container(
                    width: 81.w,
                    margin: const EdgeInsets.symmetric(horizontal: 15),
                    padding: const EdgeInsets.all(0),
                    decoration: BoxDecoration(
                      gradient: const LinearGradient(
                          begin: Alignment.topLeft,
                          end: Alignment.bottomRight,
                          colors: [
                            Color(0xff302E2E),
                            Color(0xe6444141),
                            Color(0x8c484646),
                            Color(0x26444141),
                          ]),
                      borderRadius: BorderRadius.circular(10),
                    ),
                    child: ElevatedButton(
                      onPressed: () {
                        _onBooking();
                      },
                      style: ElevatedButton.styleFrom(
                        foregroundColor: Colors.black,
                        backgroundColor: Colors.black12,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10.0),
                        ),
                        minimumSize: const Size(200, 50),
                        padding: const EdgeInsets.all(0),
                        shadowColor: Colors.transparent,
                      ),
                      child: const Text(
                        'Đặt Lịch',
                        style: TextStyle(
                            fontSize: 24,
                            color: Colors.white,
                            letterSpacing: 1.5,
                            fontWeight: FontWeight.w700),
                      ),
                    ),
                  ),
                  const Center(
                    child: Text("Cắt xong trả tiền, hủy lịch không sao"),
                  )
                ],
              )
            : Container(),

        const SizedBox(
          height: 20,
        ),
      ],
    ));
  }

  @override
  bool get wantKeepAlive => true;
  BranchModel selectedBranch = BranchModel();
  List<BranchServiceModel> selectedService = [];
  AccountInfoModel? selectedStylist;
  dynamic selectedDate;
  dynamic selectedTime;
  bool isUpdateBranch = false;
  List<AccountInfoModel>? massuerList;
// BranchModel branch
  void updateSelectedBranch(dynamic branch) {
    if (!_isDisposed) {
      selectedBranch = branch;
      if (selectedBranch.accountStaffList != null) {
        massuerList =
            List<AccountInfoModel>.from(selectedBranch.accountStaffList!);
        massuerList!
            .removeWhere((account) => account.staff!.professional == "STYLIST");
        selectedBranch.accountStaffList!
            .removeWhere((account) => account.staff!.professional == "MASSEUR");
      }
      setState(() {
        selectedBranch = branch;
        if (selectedBranch.accountStaffList != null) {
          selectedBranch.accountStaffList!.removeWhere(
              (account) => account.staff!.professional == "MASSEUR");
        }
        selectedService = [];
        isUpdateBranch = true;
        massuerList;
      });
    }
  }

// String stylist
  void updateSelectedStylist(AccountInfoModel? stylist) {
    if (!_isDisposed) {
      setState(() {
        if (stylist != null) {
          selectedStylist = stylist;
        } else {
          selectedStylist = null;
        }
      });
    }
  }

// List String service
  void updateSelectedService(List<BranchServiceModel> service) {
    if (!_isDisposed) {
      setState(() {
        selectedService = service;
        isUpdateBranch = false;

        print(selectedService);
      });
    }
  }

// Map<String, dynamic> date
  void updateSelectedDate(dynamic date) {
    if (!_isDisposed) {
      setState(() {
        selectedDate = date;
        print(selectedDate);
      });
    }
  }

  void updateSelectedTime(dynamic time) {
    if (!_isDisposed) {
      setState(() {
        selectedTime = time;
        print(selectedTime);
      });
    }
  }

  //
  bool oneToOne = false;
  List<BookingServiceModel> postBooking = [];
  void updatePostBooking(List<BookingServiceModel> postBooking) {
    if (!_isDisposed) {
      setState(() {
        this.postBooking = postBooking;
      });
    }
  }

  // change optione
  void updateOption(bool oneToOne) {
    if (!_isDisposed) {
      setState(() {
        this.oneToOne = oneToOne;
      });
    }
  }

  bool _isDisposed = false;
  @override
  void dispose() {
    _isDisposed = true;
    super.dispose();
  }

  @override
  void initState() {
    super.initState();
    // getDate();
    print(selectedDate);
    print(selectedService);
  }

  getDate() {
    DateTime now = DateTime.now();
    if (!_isDisposed) {
      setState(() {
        selectedDate = formatDate(now.add(const Duration(days: 0)));
      });
    }
  }

  formatDate(DateTime date) {
    String day = DateFormat('EEEE').format(date);
    day = formatDay(day);
    return "$day, ${DateFormat('dd/MM/yyyy').format(date)}";
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

  void _errorMessage(String? message) {
    try {
      ShowSnackBar.ErrorSnackBar(context, message!);
    } catch (e) {
      print(e);
    }
  }

  _onBooking() {
    if (selectedBranch.branchId == null) {
      _errorMessage("Xin chọn chi nhánh");
    } else if (selectedService.isEmpty) {
      _errorMessage("Xin chọn dịch vụ");
    } else if (selectedDate == null) {
      _errorMessage("Xin chọn ngày");
    } else if (selectedTime == null || selectedTime == "") {
      _errorMessage("Xin chọn giờ");
    } else {
      Get.to(() => BookingHaircutTemporary(
          callback: widget.callback,
          branch: selectedBranch,
          service: selectedService,
          stylist: selectedStylist,
          date: selectedDate,
          time: selectedTime,
          oneToOne: oneToOne,
          postBooking: postBooking));
    }
  }
}
