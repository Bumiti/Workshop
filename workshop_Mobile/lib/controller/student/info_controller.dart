import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:get/get.dart';
import 'package:workshop_mobi/api/api_service.dart';
import 'package:flutter/material.dart';

class StudentController extends GetxController {
  final ApiService apiService = ApiService();
  final storage = const FlutterSecureStorage();

  Future<void> inforStudent() async {
    try {
      // Retrieve the token from secure storage
      var accessToken = await storage.read(key: 'token');
      // Use the token in your API request
      var response = await apiService.getinforStudent(accessToken!);
      print(response);
      // if (response['status'] == 'Success') {
      //   showDialog(
      //     context: Get.context!,
      //     builder: (context) {
      //       return SimpleDialog(
      //         title: Text(response['status']),
      //         contentPadding: const EdgeInsets.all(20),
      //         children: [Text(response['message'])],
      //       );
      //     },
      //   );
      // } else if (response['status'] == 'failed') {
      //   showDialog(
      //     context: Get.context!,
      //     builder: (context) {
      //       return SimpleDialog(
      //         title: Text(response['status']),
      //         contentPadding: const EdgeInsets.all(20),
      //         children: [Text(response['message'])],
      //       );
      //     },
      //   );
      // }
    } catch (error) {
      Get.back();
      showDialog(
        context: Get.context!,
        builder: (context) {
          return const SimpleDialog(
            title: Text('Error'),
            contentPadding: EdgeInsets.all(20),
            children: [Text('Please check again')],
          );
        },
      );
    }
  }
}
