import 'dart:convert';
import 'dart:math';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:realmen_customer_application/models/branch/branch_model.dart';
import 'package:realmen_customer_application/screens/message/success_screen.dart';
import 'package:realmen_customer_application/service/branch/branch_service.dart';
import 'package:sizer/sizer.dart';
import 'package:dropdown_button2/dropdown_button2.dart';

class ListBranchesScreen extends StatefulWidget {
  ListBranchesScreen({super.key});
  var city = Get.arguments;

  @override
  State<ListBranchesScreen> createState() => _ListBranchesScreenState();
  static const String ListBranchesScreenRoute = "/list-branches-screen";
}

class _ListBranchesScreenState extends State<ListBranchesScreen> {
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
                      height: 90.w,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(20),
                        color: Colors.white,
                      ),
                      child: ListView(
                        children: <Widget>[
                          Container(
                            padding: const EdgeInsets.only(left: 7),
                            child: Center(
                              child: Stack(
                                children: [
                                  SizedBox(
                                    height: 50,
                                    child: IconButton(
                                      alignment: Alignment.centerLeft,
                                      color: Colors.black,
                                      iconSize: 22,
                                      icon:
                                          const Icon(Icons.keyboard_arrow_left),
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
                                        style: GoogleFonts.quicksand(
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
                                  height: 180,
                                  decoration:
                                      const BoxDecoration(color: Colors.black)),
                              Image.asset(
                                "assets/images/Logo-White-NoBG-O-15.png",
                                width: 360,
                                height: 180,
                              ),
                              Container(
                                height: 180,
                                padding:
                                    const EdgeInsets.symmetric(horizontal: 20),
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.center,
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceEvenly,
                                  children: [
                                    Text(
                                      "HỆ THỐNG CHI NHÁNH CỦA REALMEN",
                                      style: GoogleFonts.quicksand(
                                        color: Colors.white,
                                        fontWeight: FontWeight.w700,
                                        fontSize: 18,
                                      ),
                                    ),
                                    Text(
                                      "Tính đến hiện tại, chuỗi barber tóc nam RealMen có 99 barber tại những vị trí đắc địa nhất TP. Hồ Chí Minh, Hà Nội và các tỉnh lân cận. Hãy tìm đến barber RealMen gần bạn nhất để tận hưởng trải nghiệm cắt tóc nam đỉnh cao!",
                                      style: GoogleFonts.quicksand(
                                        color: Colors.white,
                                        fontWeight: FontWeight.w400,
                                        fontSize: 16,
                                      ),
                                    )
                                  ],
                                ),
                              )
                            ],
                          ),
                          const SizedBox(
                            height: 30,
                          ),
                          SizedBox(
                            // width: 80.w,
                            // height: 50,
                            child: FocusScope(
                              node: focusScopeNode,
                              child: Autocomplete<BranchModel>(
                                displayStringForOption: displayStringForOption,
                                // initialValue: null,
                                optionsBuilder: (textEditingValue) async {
                                  _searchingWithQuery = textEditingValue.text;
                                  if (textEditingValue.text.isEmpty ||
                                      textEditingValue.text == '') {
                                    return const Iterable.empty();
                                  }
                                  if (widget.city == "Thành Phố/Tỉnh") {
                                    final value = await BranchService()
                                        .getBranches(textEditingValue.text);
                                    if (value['statusCode'] == 200) {
                                      branchesModel = value['data'];
                                      try {
                                        options = (await value)['data']
                                            ?.content! as Iterable<BranchModel>;

                                        return Future.value(options);
                                      } catch (e) {
                                        print(e);
                                      }
                                    }
                                  } else {
                                    if (branchesForCity != null) {
                                      options = branchesForCity!.where(
                                          (element) => utf8
                                              .decode(element.address!.runes
                                                  .toList())
                                              .toLowerCase()
                                              .contains(textEditingValue.text
                                                  .toLowerCase()));

                                      return Future.value(options);
                                    }
                                  }

                                  return [];
                                },
                                onSelected: (address) {
                                  setState(() {
                                    branchesForCity = [];
                                    (branchesForCity as List<BranchModel>)
                                        ?.add(address);
                                    focusScopeNode.unfocus();
                                    isSearching = true;
                                  });
                                  debugPrint('You just selected $address');
                                },
                                fieldViewBuilder: (context, controller,
                                    focusNode, onEditingComplete) {
                                  return Container(
                                    padding: const EdgeInsets.symmetric(
                                        horizontal: 10),
                                    child: TextField(
                                      // controller: firstNameController,
                                      controller: controller,
                                      focusNode: focusNode,
                                      onEditingComplete: onEditingComplete,
                                      onSubmitted: (value) async {
                                        searchBranches(value, focusNode);
                                        focusNode.requestFocus();
                                      },

                                      cursorColor: Colors.black,
                                      cursorWidth: 1,
                                      style: const TextStyle(
                                          height: 1.17,
                                          fontSize: 20,
                                          color: Colors.black),
                                      decoration: InputDecoration(
                                        prefixIcon: const Icon(Icons.search),
                                        suffixIcon: buildSuffixIcon(
                                            controller, focusNode),
                                        enabledBorder: OutlineInputBorder(
                                          borderSide: const BorderSide(
                                              color: Color(0xffC4C4C4)),
                                          borderRadius:
                                              BorderRadius.circular(7),
                                        ),
                                        focusedBorder: OutlineInputBorder(
                                          borderSide: const BorderSide(
                                              color: Color(0xffC4C4C4)),
                                          borderRadius:
                                              BorderRadius.circular(7),
                                        ),
                                        contentPadding: const EdgeInsets.only(
                                            // top: 10,
                                            // bottom: 20,
                                            left: 15,
                                            right: 15),
                                        hintText:
                                            "Tìm kiếm tên chi nhánh và địa điểm",
                                        hintStyle: const TextStyle(
                                            fontSize: 20,
                                            fontWeight: FontWeight.w400,
                                            color: Color(0xffC4C4C4)),
                                      ),
                                    ),
                                  );
                                },
                              ),
                            ),
                          ),
                          const SizedBox(
                            height: 15,
                          ),
                          Row(
                            children: [
                              Container(
                                height: 40,
                                width: 190,
                                margin:
                                    const EdgeInsets.symmetric(horizontal: 10),
                                decoration: BoxDecoration(
                                  border: Border.all(
                                    color: const Color(0xff8E1D1D),
                                    width: 0.5,
                                    style: BorderStyle.solid,
                                  ),
                                  borderRadius: BorderRadius.circular(5),
                                  color: Colors.white,
                                ),
                                child: TextButton(
                                  style: const ButtonStyle(),
                                  onPressed: () {},
                                  child: const Row(
                                    mainAxisAlignment: MainAxisAlignment.start,
                                    children: [
                                      Icon(Icons.location_on),
                                      Text("Tìm chi nhánh gần anh"),
                                    ],
                                  ),
                                ),
                              ),
                              Container(
                                height: 40,
                                width: 160,
                                padding: const EdgeInsets.only(left: 5),
                                decoration: BoxDecoration(
                                  border: Border.all(
                                    color: Colors.black,
                                    width: 1,
                                    style: BorderStyle.solid,
                                  ),
                                  borderRadius: BorderRadius.circular(5),
                                  color: Colors.white,
                                ),
                                child: DropdownButtonHideUnderline(
                                  child: DropdownButton2<String>(
                                    isExpanded: true,
                                    hint: Text(
                                      'Thành phố/Tỉnh',
                                      style: TextStyle(
                                        // fontSize: 14,
                                        color: Theme.of(context).hintColor,
                                      ),
                                    ),
                                    alignment: Alignment.center,
                                    value: cityController,
                                    items: cities != null && cities.length != 0
                                        ? cities!
                                            .map((city) =>
                                                DropdownMenuItem<String>(
                                                  value: city,
                                                  child: Text(
                                                    city,
                                                    style: const TextStyle(
                                                      fontSize: 14,
                                                      overflow:
                                                          TextOverflow.ellipsis,
                                                    ),
                                                  ),
                                                ))
                                            .toList()
                                        : [],
                                    onChanged: (city) => setState(() {
                                      cityController = city!;
                                      widget.city = city;
                                      getBranches();
                                    }),
                                    dropdownStyleData: DropdownStyleData(
                                      maxHeight: 160,
                                      width: 160,
                                      // padding: EdgeInsets.only(right: 5),
                                      decoration: BoxDecoration(
                                        // borderRadius: BorderRadius.circular(14),
                                        color: Colors.grey.shade200,
                                      ),
                                      offset: const Offset(-5, -2),
                                      scrollbarTheme: ScrollbarThemeData(
                                        // radius: const Radius.circular(40),
                                        // thickness: MaterialStateProperty.all(6),
                                        thumbVisibility:
                                            MaterialStateProperty.all(true),
                                      ),
                                    ),
                                    menuItemStyleData: const MenuItemStyleData(
                                      height: 40,
                                      padding:
                                          EdgeInsets.only(left: 14, right: 14),
                                    ),
                                  ),
                                ),
                              ),
                            ],
                          ),
                          const SizedBox(
                            height: 30,
                          ),
                          branchesForCity != null
                              ? ListView.builder(
                                  shrinkWrap: true,
                                  physics: const NeverScrollableScrollPhysics(),
                                  itemCount: branchesForCity?.length,
                                  itemBuilder: (context, index) {
                                    return Container(
                                      // height: 210,
                                      // width: double.infinity,
                                      child: Column(
                                        children: [
                                          Image.asset(
                                            image,
                                            // width: double.infinity,
                                            // height: double.infinity,
                                            width: MediaQuery.of(context)
                                                    .size
                                                    .width /
                                                1.2,

                                            height: 140,
                                            fit: BoxFit.cover,
                                          ),
                                          ListTile(
                                            title: Text(utf8.decode(
                                                branchesForCity![index]
                                                    .branchName
                                                    .toString()
                                                    .runes
                                                    .toList())),
                                            subtitle: Text(utf8.decode(
                                                branchesForCity![index]
                                                    .address
                                                    .toString()
                                                    .runes
                                                    .toList())),
                                            trailing: Container(
                                              height: 40,
                                              width: 90,
                                              decoration: const BoxDecoration(
                                                color: Color(0xffE3E3E3),
                                                borderRadius: BorderRadius.all(
                                                  Radius.circular(4),
                                                ),
                                              ),
                                              child: ElevatedButton(
                                                onPressed: () {
                                                  // Xử lý sự kiện khi nhấn nút đặt lịch
                                                },
                                                style: ElevatedButton.styleFrom(
                                                  shape: RoundedRectangleBorder(
                                                    borderRadius:
                                                        BorderRadius.circular(
                                                            4),
                                                  ),
                                                  backgroundColor:
                                                      Colors.transparent,
                                                  shadowColor:
                                                      Colors.transparent,
                                                ),
                                                child: const Text(
                                                  'Đặt lịch',
                                                  style: TextStyle(
                                                    fontSize: 17,
                                                    color: Colors.black,
                                                  ),
                                                ),
                                              ),
                                            ),
                                          ),
                                          const SizedBox(
                                            height: 10,
                                          ),
                                          (index != branchesForCity!.length - 1)
                                              ? const Divider(
                                                  color: Color(0x73444444),
                                                  height: 1,
                                                  thickness: 1,
                                                )
                                              : const SizedBox.shrink(),
                                        ],
                                      ),
                                    );
                                  },
                                )
                              : Container(),
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
    );
  }

  // Logic
  @override
  void initState() {
    super.initState();
    getBranchesByCity();
    cityController = widget.city;
  }

  @override
  void dispose() {
    focusNode.dispose();
    super.dispose();
  }

  String image = "assets/images/branch1.png";
  BranchesByCityModel? branchesByCityModel = BranchesByCityModel();
  List<BranchModel>? branchesForCity;
  String? cityController;
  List<String> cities = [];
  Future<void> getBranchesByCity() async {
    try {
      BranchService branchService = BranchService();
      final result = await branchService.getBranchesByCity();
      if (result['statusCode'] == 200) {
        branchesByCityModel = result['data'] as BranchesByCityModel;
        try {
          if (branchesByCityModel != null) {
            getBranches();
            cities.add("Thành Phố/Tỉnh");
            if (branchesByCityModel?.values != null) {
              for (var values in branchesByCityModel!.values!) {
                cities.add(utf8
                    .decode(values.city.toString().runes.toList())
                    .toString());
              }
            }
          }
        } on Exception catch (e) {
          print(e);
        }
        setState(() {
          cities;
        });
      } else {
        _errorMessage("$result['statusCode'] : $result['error']");
      }
    } on Exception catch (e) {
      _errorMessage(e.toString());
      print("Error: $e");
    }
  }

  void _errorMessage(String? message) {
    try {
      ShowSnackBar.ErrorSnackBar(context, message!);
    } catch (e) {
      print(e);
    }
  }

  getBranches() {
    branchesForCity = [];
    try {
      if (branchesByCityModel?.values != null) {
        if (widget.city == "Thành Phố/Tỉnh") {
          (branchesByCityModel?.values as List)
              ?.map((e) =>
                  (branchesForCity as List<BranchModel>)?.addAll(e.branches))
              ?.toList();
        } else {
          for (var values in branchesByCityModel!.values!) {
            if (utf8.decode(values.city.toString().runes.toList()) ==
                widget.city) {
              branchesForCity = values.branchList;
            }
          }
        }
      }
      setState(() {
        branchesForCity;
      });
    } on Exception catch (e) {
      _errorMessage(e.toString());
      print("Error: $e");
    }
  }

  bool isSearching = false;
  FocusNode focusNode = FocusNode();
  FocusScopeNode focusScopeNode = FocusScopeNode();

  Future<void> searchBranches(String query, FocusNode focusNode) async {
    try {
      BranchService branchService = BranchService();
      final result = await branchService.getBranches(query);
      if (result['statusCode'] == 200) {
        branchesModel = result['data'] as BranchesModel;
        try {
          branchesForCity = [];
          for (var branch in branchesModel!.content!) {
            (branchesForCity as List<BranchModel>)?.add(branch);
          }
        } on Exception catch (e) {
          print(e);
        }
        setState(() {
          branchesForCity;
          isSearching = true;
          focusNode.unfocus();
        });
      } else {
        _errorMessage("$result['statusCode'] : $result['error']");
      }
    } on Exception catch (e) {
      _errorMessage(e.toString());
      print("Error: $e");
    }
  }

  IconButton? buildSuffixIcon(controller, focusNode) {
    return isSearching || focusNode.hasFocus
        ? IconButton(
            onPressed: () {
              setState(() {
                controller.clear();
                isSearching = false;
              });
            },
            icon: Icon(Icons.clear),
          )
        : null;
  }

  String displayStringForOption(BranchModel branch) =>
      utf8.decode(branch.address!.runes.toList());
  String? _searchingWithQuery;
  Iterable<BranchModel>? options;
  BranchModel branchModel = BranchModel();
  BranchesModel branchesModel = BranchesModel();

  BranchModel selectedAddress = BranchModel();
}