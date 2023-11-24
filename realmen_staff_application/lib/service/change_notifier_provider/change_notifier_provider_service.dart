import 'package:flutter/material.dart';

class ChangeNotifierServices extends ChangeNotifier {
  List<String> _selectedServices = [];
  dynamic _selectedBranch;
  dynamic _selectedStylist;

  List<String> get selectedServices => _selectedServices;
  String get selectedBranch => _selectedBranch;
  dynamic get selectedStylist => _selectedStylist;

  void updateSelectedServices(List<String> services) {
    _selectedServices = services;
    notifyListeners();
  }

  void updateSelectedBranch(dynamic branch) {
    _selectedBranch = branch;
    notifyListeners();
  }

  void updateSelectedStylist(dynamic stylist) {
    _selectedStylist = stylist;
    notifyListeners();
  }
}
