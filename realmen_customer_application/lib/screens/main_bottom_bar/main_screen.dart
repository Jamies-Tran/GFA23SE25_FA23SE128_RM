// ignore_for_file: constant_identifier_names

import 'package:curved_labeled_navigation_bar/curved_navigation_bar.dart';
import 'package:flutter/material.dart';

import 'package:curved_labeled_navigation_bar/curved_navigation_bar_item.dart';
import 'package:community_material_icon/community_material_icon.dart';
import 'package:get/get.dart';
import 'package:realmen_customer_application/screens/booking/booking_screen.dart';
import 'package:realmen_customer_application/screens/home/home_screen.dart';
import 'package:realmen_customer_application/screens/login/login_phone_screen.dart';
import 'package:realmen_customer_application/screens/membership/membership_screen.dart';
import 'package:realmen_customer_application/screens/profile/profile_screen.dart';
import 'package:realmen_customer_application/screens/service_price_list/service_price_list_screen.dart';
import 'package:realmen_customer_application/service/share_prreference/share_prreference.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({Key? key}) : super(key: key);

  @override
  State<MainScreen> createState() => _MainScreenState();
  static const String MainScreenRoute = "/main-screen";
}

class _MainScreenState extends State<MainScreen> {
  int bottomIndex = 0;
  final GlobalKey<CurvedNavigationBarState> _bottomNavigationKey = GlobalKey();

  late final HomeScreen homeScreen;
  late final ServicePriceListScreen servicePriceListScreen;
  late final MembershipScreen membershipScreen;
  late final BookingScreen bookingScreen;
  late final ProfileScreen profileScreen;

  void setPage(index) {
    final CurvedNavigationBarState? navBarState =
        _bottomNavigationKey.currentState;
    navBarState?.setPage(index);
  }

  @override
  void initState() {
    homeScreen = HomeScreen(setPage);
    servicePriceListScreen = ServicePriceListScreen(setPage);
    bookingScreen = BookingScreen(setPage);
    membershipScreen = MembershipScreen(setPage);
    profileScreen = ProfileScreen(setPage);
    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
  }

  pageChooser(int page) {
    try {
      switch (page) {
        case 0:
          return homeScreen;
        case 1:
          return servicePriceListScreen;
        case 2:
          return bookingScreen;
        case 3:
          return membershipScreen;
        case 4:
          return profileScreen;
      }
    } catch (e) {
      return homeScreen;
    }
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        // key: _bottomNavigationKey,
        body: pageChooser(bottomIndex),
        bottomNavigationBar: CurvedNavigationBar(
          key: _bottomNavigationKey,
          color: Colors.white,
          backgroundColor: Colors.black87,
          items: const [
            CurvedNavigationBarItem(
              child: Icon(Icons.home_outlined),
              label: 'Trang chủ',
            ),
            CurvedNavigationBarItem(
              child: Icon(Icons.list_alt),
              label: 'Bảng giá',
            ),
            CurvedNavigationBarItem(
              child: Icon(Icons.calendar_month),
              label: 'Đặt lịch',
            ),
            CurvedNavigationBarItem(
              // child: Icon(Icons.newspaper),
              child: Icon(CommunityMaterialIcons.crown),
              label: 'RM Member',
            ),
            CurvedNavigationBarItem(
              child: Icon(Icons.perm_identity),
              label: 'Tài khoản',
            ),
          ],
          onTap: (value) async {
            if (!await SharedPreferencesService.checkJwtExpired()) {
              setState(() {
                bottomIndex = value;
              });
            } else {
              Get.toNamed(LoginPhoneScreen.LoginPhoneScreenRoute);
            }
          },
        ),
      ),
    );
  }
}
