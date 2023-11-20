import 'package:workshop_mobi/screens/auth/auth_screen.dart';
import 'package:flutter/material.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';
import 'package:workshop_mobi/screens/auth/login_or_register.dart';

void main() async {
  runApp(const GetMaterialApp(
    debugShowCheckedModeBanner: false,
    home: LoginOrReg(),
  ));
}
