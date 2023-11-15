import 'package:workshop_mobi/api/api_service.dart';
import 'package:workshop_mobi/model/user.dart';
import 'package:workshop_mobi/screens/auth/auth_screen.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';

class TeacherHomeScreen extends StatefulWidget {
  const TeacherHomeScreen({Key? key}) : super(key: key);

  @override
  State<TeacherHomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<TeacherHomeScreen> {
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();
  final ApiService _apiSer = ApiService();
  List<User> userList = [];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(actions: [
        TextButton(
          onPressed: () async {
            final SharedPreferences? prefs = await _prefs;
            prefs?.clear();
            Get.offAll(AuthScreen());
          },
          child: const Text(
            'logout',
            style: TextStyle(color: Colors.white),
          ),
        )
      ]),
      body:const Center(
        child: Column(
          children: [
             Text('Welcome Teacher Home'),
          ],
        ),
      ),
    );
  }
}