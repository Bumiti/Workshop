
import 'package:flutter/material.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';
import 'package:workshop_mobi/screens/auth/authscreen_v2.dart';

void main() async {
  
  runApp(const GetMaterialApp(
    debugShowCheckedModeBanner: false,
    home:  SignIn(),
  ));
}

