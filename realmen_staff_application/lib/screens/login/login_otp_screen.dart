// ignore_for_file: constant_identifier_names, avoid_print

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:realmen_staff_application/models/schedule/login_register/login_otp_model.dart';
import 'package:realmen_staff_application/screens/main_bottom_bar/main_screen.dart';
import 'package:realmen_staff_application/screens/message/success_screen.dart';
import 'package:realmen_staff_application/service/authentication/authenticateService.dart';
import 'package:realmen_staff_application/service/share_prreference/share_prreference.dart';
import 'package:sizer/sizer.dart';
import 'package:pinput/pinput.dart';

class LoginOTPScreen extends StatefulWidget {
  const LoginOTPScreen({super.key});
  static const String LoginOTPScreenRoute = "/login-otp-screen";

  @override
  State<LoginOTPScreen> createState() => _LoginOTPScreenState();
}

class _LoginOTPScreenState extends State<LoginOTPScreen> {
  // UI
  @override
  Widget build(BuildContext context) {
    final defaultPinTheme = PinTheme(
      width: 56,
      height: 56,
      textStyle: const TextStyle(
          fontSize: 20, color: Colors.black, fontWeight: FontWeight.w600),
      decoration: BoxDecoration(
        border: Border.all(color: const Color(0xffC4C4C4)),
        borderRadius: BorderRadius.circular(20),
      ),
    );

    final focusedPinTheme = defaultPinTheme.copyDecorationWith(
      border: Border.all(color: const Color(0xff777777)),
      borderRadius: BorderRadius.circular(8),
    );

    final submittedPinTheme = defaultPinTheme.copyWith(
      decoration: defaultPinTheme.decoration?.copyWith(
        color: const Color.fromRGBO(239, 240, 241, 1),
      ),
    );
    return Scaffold(
      body: SingleChildScrollView(
        child: SizedBox(
          height: 100.h,
          child: Stack(
            children: [
              Positioned(
                child: Container(
                  decoration: const BoxDecoration(
                    image: DecorationImage(
                      image: AssetImage('assets/images/bg.png'),
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
              ),
              SafeArea(
                child: Stack(
                  alignment: Alignment.center,
                  children: [
                    Positioned(
                      top: 10.h,
                      child: Container(
                        // padding: const EdgeInsets.only(top: 10),
                        // margin: EdgeInsets.symmetric(horizontal: 68),
                        width: 80.w,
                        height: 55.h,
                        decoration: BoxDecoration(
                          color: Colors.white,
                          borderRadius: BorderRadius.circular(20),
                        ),
                        child: SingleChildScrollView(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceAround,
                            children: [
                              Column(
                                children: <Widget>[
                                  SizedBox(
                                    height: 5.h,
                                  ),
                                  Image.asset(
                                    'assets/images/logo.png',
                                    width: 257,
                                    // height: 478,
                                  ),
                                  SizedBox(
                                    height: 5.h,
                                  ),
                                  const Text(
                                    "ĐĂNG NHẬP",
                                    style: TextStyle(
                                      fontSize: 35,
                                      fontWeight: FontWeight.w700,
                                      color: Color(0xff444444),
                                    ),
                                  ),
                                  SizedBox(
                                    height: 2.h,
                                  ),
                                  const Text(
                                    "Nhập OTP",
                                    style: TextStyle(
                                      fontSize: 27,
                                      fontWeight: FontWeight.w400,
                                      color: Color(0xff444444),
                                    ),
                                  ),
                                  SizedBox(
                                    height: 2.h,
                                  ),
                                  Pinput(
                                    defaultPinTheme: defaultPinTheme,
                                    focusedPinTheme: focusedPinTheme,
                                    submittedPinTheme: submittedPinTheme,
                                    length: 5,
                                    pinputAutovalidateMode:
                                        PinputAutovalidateMode.onSubmit,
                                    showCursor: true,
                                    onCompleted: (pin) => submitOtp(),
                                    controller: otpController,
                                  ),
                                  SizedBox(
                                    height: 2.5.h,
                                  ),
                                  Container(
                                    margin: const EdgeInsets.only(top: 22),
                                    width: 70.w,
                                    height: 40,
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
                                      borderRadius: BorderRadius.circular(24),
                                    ),
                                    child: ElevatedButton(
                                      onPressed: submitOtp,
                                      // No API
                                      // () => Get.toNamed(
                                      //     MainScreen.MainScreenRoute),
                                      style: ElevatedButton.styleFrom(
                                        shape: RoundedRectangleBorder(
                                          borderRadius:
                                              BorderRadius.circular(24),
                                        ),
                                        backgroundColor: Colors.transparent,
                                        shadowColor: Colors.transparent,
                                      ),
                                      child: const Text(
                                        "ĐĂNG NHẬP",
                                        style: TextStyle(
                                            fontSize: 24,
                                            color: Color(0xffC4C4C4),
                                            fontWeight: FontWeight.w700),
                                      ),
                                    ),
                                  ),
                                ],
                              )
                            ],
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  // Logic
  TextEditingController otpController = TextEditingController();
  void submitOtp() async {
    String phone = await SharedPreferencesService.getPhone();
    // ignore: unused_local_variable
    String otp = otpController.text.toString();
    // LoginOtpModel loginOtpModel = LoginOtpModel(phone: phone, passCode: otp);
    LoginOtpModel loginOtpModel =
        LoginOtpModel(phone: phone, passCode: "12345");
    // LoginOtpModel loginOtpModel =
    //     LoginOtpModel(phone: "0917901486", passCode: "12345");
    AuthenticateService authenticateService = AuthenticateService();
    try {
      var result = await authenticateService.loginOtp(loginOtpModel);
      if (result != null && result['statusCode'] == 200) {
        try {
          if (result['data'].loginOtpResponseModel.jwtToken != null) {
            _successMessage("Đăng nhập thành công");
            // Navigator.pushNamed(context, MainScreen.MainScreenRoute);
            Get.toNamed(MainScreen.MainScreenRoute);
          } else {
            _errorMessage(result['error']);
          }
        } catch (e) {
          _errorMessage("Sai mã OTP");
        }
      } else {
        _errorMessage("$result['statusCode'] : $result['error']");
      }
    } catch (e) {
      print("Error: $e");
    }
  }

  void _successMessage(String? message) {
    try {
      ShowSnackBar.SuccessSnackBar(context, message!);
    } catch (e) {
      print(e);
    }
  }

  void _errorMessage(String? message) {
    try {
      ShowSnackBar.ErrorSnackBar(context, message!);
    } catch (e) {
      print(e);
    }
  }
}
