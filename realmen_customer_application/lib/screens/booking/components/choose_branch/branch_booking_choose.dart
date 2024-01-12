// ignore_for_file: prefer_if_null_operators

import 'dart:convert';

import 'package:community_material_icon/community_material_icon.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:timeline_tile/timeline_tile.dart';

import 'package:realmen_customer_application/screens/booking/components/choose_branch/choose_branch_screen.dart';
import 'package:realmen_customer_application/service/change_notifier_provider/change_notifier_provider_service.dart';

class ChooseBranchBooking extends StatefulWidget {
  final void Function(dynamic branch) onBranchSelected;
  const ChooseBranchBooking({
    super.key,
    required this.onBranchSelected,
  });

  @override
  State<ChooseBranchBooking> createState() => _ChooseBranchBookingState();
}

class _ChooseBranchBookingState extends State<ChooseBranchBooking> {
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
              "1. Chọn barber ",
              style: TextStyle(fontSize: 20),
            ),
            const SizedBox(
              height: 10,
            ),
            ElevatedButton(
              onPressed: () async {
                if (!_isDisposed) {
                  var selectedBranch = await Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) =>
                          ChangeNotifierProvider<ChangeNotifierServices>.value(
                        value: selectedServicesProvider,
                        child: ChooseBranchesScreen(),
                      ),
                    ),
                  );
                  if (selectedBranch != null) {
                    if (!_isDisposed) {
                      setState(() {
                        buttonText = selectedBranch.address != null
                            ? utf8.decode(selectedBranch.address
                                .toString()
                                .runes
                                .toList())
                            : 'Tất cả Barber REALMEN';
                        widget.onBranchSelected(selectedBranch);
                      });
                    }
                  }
                }

                // Get.toNamed(ChooseBranchesScreen.ChooseBranchesScreenRoute);
              },
              style: ElevatedButton.styleFrom(
                  foregroundColor: Colors.grey,
                  backgroundColor: Colors.white,
                  side: const BorderSide(color: Colors.grey),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(5.0),
                  ),
                  padding: const EdgeInsets.only(right: 0, left: 10)),
              child: Container(
                // color: Colors.amber,
                padding: const EdgeInsets.all(0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Flexible(
                      child: ClipRRect(
                        child: Row(
                          children: [
                            const Align(
                              alignment: Alignment.centerLeft,
                              child: Icon(
                                CommunityMaterialIcons.storefront,
                                color: Colors.black,
                                size: 24,
                              ),
                            ),
                            Flexible(
                              child: ClipRRect(
                                child: Container(
                                  margin: const EdgeInsets.only(left: 10.0),
                                  child: Text(
                                    buttonText,
                                    maxLines: 1,
                                    style: const TextStyle(
                                        overflow: TextOverflow.ellipsis,
                                        fontSize: 15,
                                        fontWeight: FontWeight.w400,
                                        color: Colors.black),
                                  ),
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                    const Align(
                        alignment: Alignment.centerRight,
                        child: Icon(Icons.arrow_right, color: Colors.black))
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  // Logic
  @override
  void initState() {
    super.initState();
  }

  bool isActived = true;
  String buttonText = 'Tất cả Barber REALMEN';
  ChangeNotifierServices selectedServicesProvider = ChangeNotifierServices();
  bool hasSelectedServices = false;
  bool _isDisposed = false;
  @override
  void dispose() {
    _isDisposed = true;
    super.dispose();
  }
}
