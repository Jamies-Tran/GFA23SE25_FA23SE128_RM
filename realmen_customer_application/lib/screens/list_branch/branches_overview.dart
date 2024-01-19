// ignore_for_file: constant_identifier_names, avoid_print, prefer_conditional_assignment

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:realmen_customer_application/models/branch/branch_model.dart';
import 'package:realmen_customer_application/screens/list_branch/list_branches.dart';
import 'package:realmen_customer_application/screens/message/success_screen.dart';
import 'package:realmen_customer_application/service/branch/branch_service.dart';
import 'package:sizer/sizer.dart';

class BranchesOverviewScreen extends StatefulWidget {
  const BranchesOverviewScreen({super.key});

  @override
  State<BranchesOverviewScreen> createState() => _BranchesOverviewScreenState();
  static const String BranchesOverviewScreenRoute = "/branches-overview-screen";
}

class _BranchesOverviewScreenState extends State<BranchesOverviewScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Positioned(
            child: Container(
              decoration: const BoxDecoration(
                image: DecorationImage(
                    image: AssetImage('assets/images/bg.png'),
                    fit: BoxFit.cover),
              ),
            ),
          ),
          SafeArea(
              child: Stack(
            alignment: Alignment.center,
            children: [
              Positioned(
                top: 15,
                bottom: 27,
                child: ClipRRect(
                  borderRadius: BorderRadius.circular(20),
                  child: Container(
                    padding: const EdgeInsets.only(top: 15, left: 0),
                    width: 90.w,
                    height: 90.h,
                    decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(20),
                        color: Colors.white),
                    child: ListView(
                      children: <Widget>[
                        Container(
                          padding: const EdgeInsets.only(left: 7),
                          child: Center(
                            child: Stack(
                              // mainAxisAlignment: MainAxisAlignment.center,
                              children: [
                                SizedBox(
                                  height: 50,
                                  child: IconButton(
                                    alignment: Alignment.centerLeft,
                                    color: Colors.black,
                                    iconSize: 22,
                                    icon: const Icon(Icons.keyboard_arrow_left),
                                    onPressed: () {
                                      Navigator.pop(context);
                                    },
                                  ),
                                ),
                                SizedBox(
                                  height: 50,
                                  child: Center(
                                    child: Text(
                                      "hệ thống chi nhánh".toUpperCase(),
                                      style: const TextStyle(
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
                        const SizedBox(
                          height: 30,
                        ),
                        Stack(
                          children: [
                            Container(
                                height: 160,
                                decoration:
                                    const BoxDecoration(color: Colors.black)),
                            Image.asset(
                              "assets/images/Logo-White-NoBG-O-15.png",
                              width: 360,
                              height: 160,
                            ),
                            Container(
                              height: 160,
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 20),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.center,
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceEvenly,
                                children: [
                                  Text(
                                    "các barber CỦA REALMEN".toUpperCase(),
                                    style: const TextStyle(
                                      color: Colors.white,
                                      fontWeight: FontWeight.w700,
                                      fontSize: 18,
                                    ),
                                  ),
                                  Text(
                                    "Tận hưởng trải nghiệm cắt tóc nam đỉnh \ncao tại hơn $count barber RealMen trải dài khắp \n${city1 ?? ""}${city2 != null ? ', $city2 ' : ''} ${city1 != null || city2 != null ? 'và ' : ''}các tỉnh lân cận!",
                                    style: const TextStyle(
                                      color: Colors.white,
                                      fontWeight: FontWeight.w400,
                                      fontSize: 16,
                                      height: 1.4,
                                    ),
                                    // textAlign: TextAlign.justify,
                                  )
                                ],
                              ),
                            )
                          ],
                        ),
                        const SizedBox(
                          height: 30,
                        ),
                        ListView.builder(
                          shrinkWrap: true,
                          physics: const NeverScrollableScrollPhysics(),
                          itemCount: branchesByCityModel?.values?.length ?? 0,
                          itemBuilder: (context, index) {
                            return Row(
                              children: [
                                Expanded(
                                  child: Container(
                                    // color: Colors.amberAccent,
                                    decoration: const BoxDecoration(
                                      border: Border(
                                        bottom: BorderSide(
                                            color: Color(0x4D444444),
                                            width: 1.0),
                                      ),
                                    ),
                                    child: TextButton(
                                      onPressed: () {
                                        Get.toNamed(
                                            ListBranchesScreen
                                                .ListBranchesScreenRoute,
                                            arguments: utf8.decode(
                                                branchesByCityModel!
                                                    .values![index].city!
                                                    .toString()
                                                    .runes
                                                    .toList()));
                                      },
                                      child: Row(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.center,
                                        mainAxisAlignment:
                                            MainAxisAlignment.spaceBetween,
                                        children: [
                                          Column(
                                            crossAxisAlignment:
                                                CrossAxisAlignment.start,
                                            mainAxisAlignment:
                                                MainAxisAlignment.spaceAround,
                                            children: [
                                              Padding(
                                                padding:
                                                    const EdgeInsets.symmetric(
                                                        horizontal: 10),
                                                child: Text(
                                                  utf8.decode(
                                                      branchesByCityModel!
                                                          .values![index].city!
                                                          .toString()
                                                          .runes
                                                          .toList()),
                                                  style: const TextStyle(
                                                    color: Colors.black,
                                                    fontSize: 18,
                                                  ),
                                                ),
                                              ),
                                              Padding(
                                                padding:
                                                    const EdgeInsets.symmetric(
                                                        horizontal: 10,
                                                        vertical: 5),
                                                child: Text(
                                                  'Số lượng chi nhánh: ${branchesByCityModel!.values![index].branch}',
                                                  style: const TextStyle(
                                                      color: Colors.black,
                                                      fontSize: 17,
                                                      fontWeight:
                                                          FontWeight.w300),
                                                ),
                                              ),
                                            ],
                                          ),
                                          const Icon(
                                            Icons.arrow_forward_ios,
                                            color: Colors.black87,
                                            size: 17,
                                          ),
                                        ],
                                      ),
                                    ),
                                  ),
                                ),
                              ],
                            );

                            // ExpansionTile(
                            //   title: Text(utf8.decode(branchesByCityModel!
                            //       .values![index].city!
                            //       .toString()
                            //       .runes
                            //       .toList())),
                            //   subtitle: Text(
                            //       'Số lượng chi nhánh: ${branchesByCityModel!.values![index].branch}'),
                            //   children: [
                            //     ListView.builder(
                            //       shrinkWrap: true,
                            //       physics: const NeverScrollableScrollPhysics(),
                            //       itemCount: min(
                            //           branchesByCityModel!
                            //               .values![index].branch!,
                            //           3),
                            //       itemBuilder: (context, i) {
                            //         return Container(
                            //           // height: 210,
                            //           // width: double.infinity,
                            //           child: Column(
                            //             children: [
                            //               Image.asset(
                            //                 image,
                            //                 // width: double.infinity,
                            //                 // height: double.infinity,
                            //                 width: MediaQuery.of(context)
                            //                         .size
                            //                         .width /
                            //                     1.2,

                            //                 height: 140,
                            //                 fit: BoxFit.cover,
                            //               ),
                            //               ListTile(
                            //                 title: Text(utf8.decode(
                            //                     branchesByCityModel!
                            //                         .values![index]
                            //                         .branchList![i]
                            //                         .branchName
                            //                         .toString()
                            //                         .runes
                            //                         .toList())),
                            //                 subtitle: Text(utf8.decode(
                            //                     branchesByCityModel!
                            //                         .values![index]
                            //                         .branchList![i]
                            //                         .address
                            //                         .toString()
                            //                         .runes
                            //                         .toList())),
                            //                 trailing: Container(
                            //                   height: 40,
                            //                   width: 90,
                            //                   decoration: const BoxDecoration(
                            //                     color: Color(0xffE3E3E3),
                            //                     borderRadius: BorderRadius.all(
                            //                       Radius.circular(4),
                            //                     ),
                            //                   ),
                            //                   child: ElevatedButton(
                            //                     onPressed: () {
                            //                       // Xử lý sự kiện khi nhấn nút đặt lịch
                            //                     },
                            //                     style: ElevatedButton.styleFrom(
                            //                       shape: RoundedRectangleBorder(
                            //                         borderRadius:
                            //                             BorderRadius.circular(
                            //                                 4),
                            //                       ),
                            //                       backgroundColor:
                            //                           Colors.transparent,
                            //                       shadowColor:
                            //                           Colors.transparent,
                            //                     ),
                            //                     child: const Text(
                            //                       'Đặt lịch',
                            //                       style: TextStyle(
                            //                         fontSize: 17,
                            //                         color: Colors.black,
                            //                       ),
                            //                     ),
                            //                   ),
                            //                 ),
                            //               ),
                            //               const SizedBox(
                            //                 height: 10,
                            //               ),
                            //               (i !=
                            //                       branchesByCityModel!
                            //                               .values![index]
                            //                               .branch! -
                            //                           1)
                            //                   ? const Divider(
                            //                       color: Color(0x73444444),
                            //                       height: 1,
                            //                       thickness: 1,
                            //                     )
                            //                   : const SizedBox.shrink(),
                            //             ],
                            //           ),
                            //         );
                            //       },
                            //     ),
                            //     TextButton(
                            //       onPressed: () {
                            //         Get.toNamed(
                            //             ListBranchesScreen
                            //                 .ListBranchesScreenRoute,
                            //             arguments: utf8.decode(
                            //                 branchesByCityModel!
                            //                     .values![index].city!
                            //                     .toString()
                            //                     .runes
                            //                     .toList()));
                            //       },
                            //       child: const Row(
                            //         mainAxisAlignment: MainAxisAlignment.end,
                            //         children: [
                            //           Text("Xem thêm"),
                            //           Icon(Icons.arrow_right),
                            //         ],
                            //       ),
                            //     )
                            //   ],
                            // );
                          },
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            ],
          ))
        ],
      ),
    );
  }

// Logic
  @override
  void initState() {
    super.initState();
    getBranchesByCity();
  }

  bool _isDisposed = false;
  @override
  void dispose() {
    _isDisposed = true;
    super.dispose();
  }

  String image = "assets/images/branch1.png";
  BranchesModel? branchesByCityModel = BranchesModel();
  int count = 0;
  String? city1;
  String? city2;
  Future<void> getBranchesByCity() async {
    if (!_isDisposed) {
      try {
        BranchService branchService = BranchService();
        final result = await branchService.getBranchesByCity();
        if (result['statusCode'] == 200) {
          branchesByCityModel = result['data'] as BranchesModel;
          for (BranchesValuesModel branch in branchesByCityModel!.values!) {
            count = count + branch.branch!;
            if (city1 == null) {
              city1 = utf8.decode(branch.city.toString().runes.toList());
            } else if (city2 == null) {
              city2 = utf8.decode(branch.city.toString().runes.toList());
            }
          }
          setState(() {
            branchesByCityModel;
            count;
            city1;
            city2;
          });
        } else {
          _errorMessage(result['message']);
          print(result);
        }
      } on Exception catch (e) {
        _errorMessage("Vui lòng thử lại");
        print(e.toString());
      }
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
