import 'package:flutter/material.dart';
import 'package:realmen_customer_application/screens/booking/components/choose_stylist_date_time/time-slot/time_slot.dart';
import 'package:realmen_customer_application/screens/booking/components/on_off_switch.dart';
import 'package:timeline_tile/timeline_tile.dart';

class ChooseTimeSlot extends StatefulWidget {
  final void Function(dynamic date) onDateSelected;
  final void Function(dynamic time) onTimeSelected;
  ChooseTimeSlot(
      {super.key, required this.onDateSelected, required this.onTimeSelected});

  @override
  State<ChooseTimeSlot> createState() => _ChooseTimeSlotState();
}

class _ChooseTimeSlotState extends State<ChooseTimeSlot> {
  @override
  Widget build(BuildContext context) {
    return TimelineTile(
      isLast: false,

      beforeLineStyle: isActived
          ? const LineStyle(color: Colors.black, thickness: 2)
          : const LineStyle(color: Colors.grey, thickness: 2),

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
      endChild: Container(
        padding: const EdgeInsets.only(top: 10, right: 15),
        constraints: const BoxConstraints(minHeight: 120),
        child: Column(
          // mainAxisAlignment: MainAxisAlignment.spaceAround,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              "3. Chọn ngày, giờ ",
              style: TextStyle(fontSize: 20),
            ),
            const SizedBox(
              height: 10,
            ),
            ChooseDateAndTimeSlot(
                onDateSelected: widget.onDateSelected,
                onTimeSelected: widget.onTimeSelected),
            const SizedBox(
              height: 20,
            ),
            Container(
              child: Padding(
                padding: const EdgeInsets.all(0),
                child: Column(
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        const Row(
                          crossAxisAlignment: CrossAxisAlignment.center,
                          mainAxisAlignment: MainAxisAlignment.start,
                          children: [
                            Icon(
                              Icons.person,
                              size: 24,
                            ),
                            SizedBox(
                              width: 10,
                            ),
                            Text(
                              "Yêu Cầu Tư Vấn",
                              textAlign: TextAlign.start,
                              style: TextStyle(
                                fontSize: 17,
                                fontWeight: FontWeight.w500,
                              ),
                            ),
                          ],
                        ),
                        const SizedBox(width: 50),
                        OnOffSwitch(
                          initialValue: isSwitched1,
                          onChanged: (value) {
                            setState(() {
                              isSwitched1 = value;
                            });
                          },
                        ),
                      ],
                    ),
                    SizedBox(
                      height: 6,
                    ),
                    isSwitched1
                        ? const Text(
                            "Anh cho phép các em giới thiệu về chương trình khuyến mãi, dịch vụ tốt nhất dành cho anh.",
                            style: TextStyle(fontSize: 14),
                          )
                        : const Text(
                            "Anh không cho phép các em giới thiệu về chương trình khuyến mãi, dịch vụ tốt nhất dành cho anh.",
                            style: TextStyle(fontSize: 14),
                          ),
                    const SizedBox(height: 10),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      crossAxisAlignment: CrossAxisAlignment.end,
                      children: [
                        const Row(
                          crossAxisAlignment: CrossAxisAlignment.center,
                          mainAxisAlignment: MainAxisAlignment.start,
                          children: [
                            Icon(
                              Icons.person,
                              size: 24,
                            ),
                            SizedBox(
                              width: 10,
                            ),
                            Text(
                              "Chụp Hình Sau Khi Cắt",
                              textAlign: TextAlign.start,
                              style: TextStyle(
                                fontSize: 17,
                                fontWeight: FontWeight.w500,
                              ),
                            ),
                          ],
                        ),
                        OnOffSwitch(
                          initialValue: isSwitched2,
                          onChanged: (value) {
                            setState(() {
                              isSwitched2 = value;
                            });
                          },
                        ),
                      ],
                    ),
                    SizedBox(
                      height: 6,
                    ),
                    isSwitched2
                        ? const Text(
                            "Anh cho phép các em chụp hình lưu lại kiểu tóc, để lần sau không phải mô tả lại cho thợ khác.",
                            style: TextStyle(fontSize: 14),
                          )
                        : const Text(
                            "Anh không cho phép các em chụp hình lưu lại kiểu tóc.",
                            style: TextStyle(fontSize: 14),
                          ),
                    SizedBox(
                      height: 15,
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  bool isActived = false;
  bool isSwitched1 = true;
  bool isSwitched2 = true;
}