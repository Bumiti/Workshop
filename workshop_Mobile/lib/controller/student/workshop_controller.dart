// import 'package:get/get.dart';
// import 'package:workshop_mobi/api/api_service.dart';
// import 'package:flutter/material.dart';


// class WordShopStudentController extends GetxController {

//   final ApiService apiService = ApiService();


//   Future<void> listWorkShopAsyn() async {
//     try {
//       var responese = await apiService.resetPassword(
//         emailController.text.trim()
//       );
//       if (responese['status'] == 'Success') {
//         showDialog(
//           context: Get.context!,
//           builder: (context) {
//             return SimpleDialog(
//               title: Text(responese['status']),
//               contentPadding: const EdgeInsets.all(20),
//               children: [Text(responese['message'])],
//             );
//           },
          
//         );
//       } else if (responese['status'] == 'failed') {
//         showDialog(
//           context: Get.context!,
//           builder: (context) {
//             return SimpleDialog(
//               title: Text(responese['status']),
//               contentPadding: const EdgeInsets.all(20),
//               children: [Text(responese['message'])],
//             );
//           },
//         );
//       }
//     } catch (error) {
//       Get.back();
//       showDialog(
//         context: Get.context!,
//         builder: (context) {
//           return const SimpleDialog(
//             title: Text('Error'),
//             contentPadding: EdgeInsets.all(20),
//             children: [Text('please check again')],
//           );
//         },
//       );
//     }
//   }
// }
