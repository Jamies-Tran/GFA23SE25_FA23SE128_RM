// ignore_for_file: library_private_types_in_public_api, constant_identifier_names, avoid_print, prefer_typing_uninitialized_variables

import 'dart:convert';
import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:sizer/sizer.dart';

import 'package:realmen_customer_application/models/branch/branch_model.dart';
import 'package:realmen_customer_application/models/categoryservice/category_model.dart';
import 'package:realmen_customer_application/service/categoryservice/category_services_service.dart';

class ChooseServiceBookingScreen extends StatefulWidget {
  @override
  _ChooseServiceBookingScreenState createState() =>
      _ChooseServiceBookingScreenState();
  static const String ChooseServiceBookingScreenRoute =
      "/choose-service-booking-screen";
  final List<BranchServiceModel> selectedServices;
  final List<BranchServiceModel> branchServiceList;

  const ChooseServiceBookingScreen({
    super.key,
    required this.selectedServices,
    required this.branchServiceList,
  });
}

class _ChooseServiceBookingScreenState
    extends State<ChooseServiceBookingScreen> {
  @override
  Widget build(BuildContext context) {
    // ignore: unused_local_variable
    NumberFormat formatter = NumberFormat("#,##0");
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
                  bottom: 70,
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(20),
                    child: Container(
                      padding: const EdgeInsets.only(top: 15, left: 0),
                      width: 90.w,
                      decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(20),
                          color: Colors.white),
                      child: ListView(
                        children: <Widget>[
                          Container(
                            color: Colors.transparent,
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
                                        "chọn dịch vụ".toUpperCase(),
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
                          Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: Text(
                              "Dịch vụ đã chọn: ${selectedServices.length}",
                              style: const TextStyle(
                                  fontSize: 18, fontWeight: FontWeight.bold),
                            ),
                          ),
                          // ServiceCategoryTile(
                          //   title: 'RealMen Combo',
                          //   serviceLists: [
                          //     SubServiceTile(
                          //       selectedServices: selectedServices,
                          //       title: 'Combo Cắt 9 bước',
                          //       description: '',
                          //       price: formatter.format(100000),
                          //       image: 'assets/images/3.png',
                          //       onSelect: (bool isSelected) {
                          //         updateSelectedServiceCount(
                          //             isSelected, 'Combo Cắt 9 bước');
                          //       },
                          //     ),
                          //     SubServiceTile(
                          //       selectedServices: selectedServices,
                          //       title: 'Combo Massage Cao Cấp',
                          //       description: '',
                          //       price: formatter.format(200000),
                          //       image: 'assets/images/massage.jpg',
                          //       onSelect: (bool isSelected) {
                          //         updateSelectedServiceCount(
                          //             isSelected, 'Combo Massage Cao Cấp');
                          //       },
                          //     ),
                          //   ],
                          //   isGridView: false,
                          // ),

                          // nội dung service list
                          isLoading
                              ? Column(
                                  crossAxisAlignment: CrossAxisAlignment.center,
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  children: [
                                    Container(
                                      margin: const EdgeInsets.only(top: 30),
                                      height: 50,
                                      width: 50,
                                      child: const CircularProgressIndicator(),
                                    )
                                  ],
                                )
                              : ListView.builder(
                                  shrinkWrap: true,
                                  physics: const NeverScrollableScrollPhysics(),
                                  itemCount: serviceCategoryTileList.length,
                                  itemBuilder: (context, index) {
                                    return ServiceCategoryTile(
                                      title: serviceCategoryTileList[index]
                                          .title
                                          .toUpperCase(),
                                      serviceLists:
                                          serviceCategoryTileList[index]
                                              .serviceLists,
                                      isGridView: true,
                                    );
                                  }),
                        ],
                      ),
                    ),
                  ),
                ),

                // Button chọn các dịch vụ và quay lại
                Positioned(
                  bottom: 12.0,
                  left: 20.0,
                  right: 20.0,
                  child: SizedBox(
                    height: 50,
                    child: ElevatedButton(
                      onPressed: () {
                        Navigator.pop(context, selectedServices);
                      },
                      style: ButtonStyle(
                        backgroundColor:
                            MaterialStateProperty.all<Color>(Colors.black),
                        shape:
                            MaterialStateProperty.all<RoundedRectangleBorder>(
                          RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(
                                8.0), // Độ bo tròn cho nút
                          ),
                        ),
                      ),
                      child: Text(
                        selectedServices.isEmpty
                            ? 'Chọn dịch vụ'.toUpperCase()
                            : 'Chọn ${selectedServices.length} dịch vụ'
                                .toUpperCase(),
                        style: const TextStyle(
                            fontSize: 20,
                            color: Colors.white,
                            fontWeight: FontWeight.w700),
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

  @override
  void initState() {
    super.initState();
    if (widget.selectedServices.isNotEmpty && widget.selectedServices != []) {
      selectedServices = widget.selectedServices;
    }
    getServices();
  }

  List<BranchServiceModel> selectedServices = [];
  bool _isDisposed = false;
  bool isLoading = true;
  @override
  void dispose() {
    _isDisposed = true;
    super.dispose();
  }

  void updateSelectedServiceCount(
      bool isSelected, BranchServiceModel serviceName) {
    if (!_isDisposed && mounted) {
      setState(() {
        if (isSelected) {
          selectedServices.add(serviceName);
        } else {
          selectedServices.remove(serviceName);
        }
      });
      print(selectedServices);
    }
  }

  List<ServiceCategoryTile> serviceCategoryTileList = [];
  final storage = FirebaseStorage.instance;
  List<String> urlList = [
    "1.jpg",
    "2.png",
    "3.png",
  ];
  Future getServices() async {
    if (!_isDisposed && mounted) {
      serviceCategoryTileList = [];
      CategoryServices categoryServices = CategoryServices();
      try {
        final result = await categoryServices.getCategoryServiceList();
        if (result['statusCode'] == 200) {
          final List<CategoryModel> categoryList = result['data'].values;
          for (var caterogy in categoryList) {
            // List<ServiceList> serviceList = [];
            if (caterogy.serviceList != null) {
              // lấy branchservice so sánh vs service trong category
              List<SubServiceTile> serviceLists = [];
              for (var branchService in widget.branchServiceList) {
                // bool check = caterogy.serviceList!.any(
                //     (service) => service.serviceId! == branchService.serviceId);
                if (caterogy.serviceList!.any((service) =>
                        service.serviceId! == branchService.serviceId) ==
                    true) {
                  String? description;
                  for (var service in caterogy.serviceList!) {
                    if (service.serviceId == branchService.serviceId) {
                      description = service.description;
                      try {
                        var reference = storage.ref(
                            '${service.serviceDisplayList![0].serviceDisplayUrl}');
                        branchService.thumbnailUrl =
                            await reference.getDownloadURL();
                      } catch (e) {
                        final random = Random();
                        var randomUrl = random.nextInt(urlList.length);
                        var reference =
                            storage.ref('service/${urlList[randomUrl]}');
                        branchService.thumbnailUrl =
                            await reference.getDownloadURL();
                      }
                    }
                  }
                  SubServiceTile subServiceTile = SubServiceTile(
                    selectedServices: selectedServices,
                    onSelect: (bool isSelected) {
                      updateSelectedServiceCount(isSelected, branchService);
                    },
                    branchService: branchService,
                    description: description,
                  );
                  serviceLists.add(subServiceTile);
                }
              }
              String title = '';
              if (caterogy.categoryType == 'HAIRCUT') {
                title = 'Cắt tóc';
              } else if (caterogy.categoryType == 'MASSAGE') {
                title = caterogy.categoryType!;
              } else {
                title = 'Dịch vụ khác';
              }
              if (serviceLists.isNotEmpty) {
                serviceCategoryTileList.add(ServiceCategoryTile(
                    title: title,
                    serviceLists: serviceLists,
                    isGridView: true));
              }
            }
          }
          if (!_isDisposed && mounted) {
            setState(() {
              serviceCategoryTileList;
              isLoading = false;
            });
          }
        } else {
          print("${result['statusCode']}  ${result['error']}");
        }
      } catch (e) {
        print(e.toString());
      }
    }
  }
}

class ServiceCategoryTile extends StatelessWidget {
  final String title;
  final List<SubServiceTile> serviceLists;
  final bool isGridView;

  const ServiceCategoryTile({
    super.key,
    required this.title,
    required this.serviceLists,
    required this.isGridView,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Padding(
          padding: const EdgeInsets.all(
              8.0), // Điều chỉnh khoảng cách xung quanh tiêu đề
          child: Container(
            padding: const EdgeInsets.symmetric(
              horizontal: 15,
              vertical: 4,
            ),
            decoration: const BoxDecoration(
              border: Border(
                left: BorderSide(
                  color: Colors.black,
                  width: 8,
                ),
              ),
            ),
            child: Text(
              title,
              style: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
          ),
        ),
        isGridView
            ? GridView.builder(
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 2, // Thay đổi giá trị này nếu cần
                  crossAxisSpacing: 8.0,
                  mainAxisSpacing: 8.0,
                  childAspectRatio: 2 / 4.2, // width : height
                ),
                shrinkWrap: true,
                physics: const NeverScrollableScrollPhysics(),
                itemCount: serviceLists.length,
                itemBuilder: (context, index) {
                  return serviceLists[index];
                },
              )
            : Column(
                children: serviceLists,
              ),
      ],
    );
  }
}

class SubServiceTile extends StatefulWidget {
  final Function(bool) onSelect; // Hàm callback
  final List<BranchServiceModel> selectedServices;
  final BranchServiceModel? branchService;

  const SubServiceTile({
    Key? key,
    required this.onSelect,
    required this.selectedServices,
    this.branchService,
  }) : super(key: key);

  @override
  State<SubServiceTile> createState() => _SubServiceTileState();
}

class _SubServiceTileState extends State<SubServiceTile> {
  bool isSelected = false;
  var selectedServicesProvider;

  @override
  void initState() {
    super.initState();

    // Khởi tạo trạng thái isSelected từ danh sách dịch vụ đã chọn
    // selectedServicesProvider =
    //     Provider.of<ChangeNotifierServices>(context, listen: false);
    // isSelected =
    //     selectedServicesProvider.selectedServices.contains(widget.title);

    // if (widget.title != null) {
    //   isSelected = widget.selectedServices.contains(widget.title);
    // } else {
    //   isSelected = widget.selectedServices.contains(widget.branchService);
    // }

    isSelected = widget.selectedServices.contains(widget.branchService);
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.all(8.0),
      padding: const EdgeInsets.all(2.0),
      decoration: BoxDecoration(
        color: Colors.black,
        border: Border.all(color: Colors.grey),
        borderRadius: BorderRadius.circular(10.0),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          ClipRRect(
            borderRadius: const BorderRadius.only(
              topRight: Radius.circular(8),
              topLeft: Radius.circular(8),
            ),
            child: CachedNetworkImage(
              imageUrl: widget.branchService!.thumbnailUrl!,
              height: 140,
              width: MediaQuery.of(context).size.width / 1.4,
              fit: BoxFit.cover,
              progressIndicatorBuilder: (context, url, progress) => Center(
                child: CircularProgressIndicator(
                  value: progress.progress,
                ),
              ),
              errorWidget: (context, url, error) => Image.asset(
                "assets/images/massage.jpg",
                height: 140,
                width: MediaQuery.of(context).size.width / 1.4,
                fit: BoxFit.cover,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 8.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                SizedBox(
                  height: 60,
                  child: Text(
                    widget.title != null
                        ? widget.title!
                        : utf8.decode(widget.branchService!.serviceName
                            .toString()
                            .runes
                            .toList()),
                    textAlign: TextAlign.start,
                    style: const TextStyle(
                        color: Colors.white,
                        fontSize: 17,
                        fontWeight: FontWeight.bold),
                  ),
                ),
                const SizedBox(height: 5),
                SizedBox(
                  height: 60,
                  child: Text(
                    widget.description == null
                        ? ''
                        : utf8.decode(
                            widget.description!.toString().runes.toList()),
                    textAlign: TextAlign.start,
                    style: const TextStyle(
                      color: Colors.white,
                      fontSize: 15,
                    ),
                  ),
                ),
                const SizedBox(height: 7),
                Text(
                  widget.price != null
                      ? ' ${widget.price!} VNĐ'
                      : (widget.branchService!.price != null
                          ? ' ${formatter.format(widget.branchService!.price)} VNĐ'
                          : (widget.branchService!.branchServicePrice != null
                              ? ' ${formatter.format(widget.branchService!.branchServicePrice)}'
                              : "0 VNĐ")),
                  style: const TextStyle(
                      color: Colors.white,
                      fontSize: 17,
                      fontWeight: FontWeight.bold),
                ),
              ],
            ),
          ),
          Container(
            width: 400,
            decoration: BoxDecoration(borderRadius: BorderRadius.circular(15)),
            child: ElevatedButton(
              onPressed: () {
                _isSelected();
              },
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all<Color>(
                  isSelected ? Colors.red : Colors.white,
                ),
                shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                  RoundedRectangleBorder(
                    borderRadius:
                        BorderRadius.circular(8.0), // Độ bo tròn cho nút
                  ),
                ),
              ),
              child: Text(
                isSelected ? 'Hủy' : 'Chọn',
                style: TextStyle(
                  fontSize: 18,
                  color: isSelected ? Colors.white : Colors.black,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }

  NumberFormat formatter = NumberFormat("#,##0");
  _isSelected() {
    setState(() {
      isSelected = !isSelected;
      widget.onSelect(isSelected);
    });
  }
}
