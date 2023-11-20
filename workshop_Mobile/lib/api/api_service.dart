import 'dart:convert';
import 'package:workshop_mobi/model/user.dart';
import 'package:workshop_mobi/utils/api_endpoints.dart';
import 'package:http/http.dart' as http;

class ApiService {
  ApiService();

  Future<dynamic> loginWebAccount(String email, String password) async {
    var headers = {'Content-Type': 'application/json'};
    try {
      var url = Uri.parse(
          ApiEndPoints.baseUrl + ApiEndPoints.authEndpoints.loginWebAccount);
          // print(url);
      Map body = {
        'email': email.trim(),
        'password': password,
      };
      http.Response response =
          await http.post(url, body: jsonEncode(body), headers: headers);
      if (response.statusCode == 200) {
        final json = jsonDecode(response.body);
        if (json['status'] == 'success') {
          var data = json['data'];
          var user = data['user'];
          return user;
        } else if (json['code'] == 1) {
          throw jsonDecode(response.body)['message'];
        }
      } else {
        throw jsonDecode(response.body)["Message"] ?? "Unknown Error Occurred";
      }
    } catch (error) {
      rethrow;
    }
  }
  //  Future<dynamic> registerAccount(String email, String password) async {
  //   var headers = {'Content-Type': 'application/json'};
  //   try {
  //     var url = Uri.parse(
  //         ApiEndPoints.baseUrl + ApiEndPoints.authEndpoints.loginWebAccount);
  //     Map body = {
  //       'full_name':email.trim(),
  //       'balance':0,
  //       'user_name':email.trim(),
  //       'email': email.trim(),
  //       'password': password,
  //       'phoneNumber':'string',
  //       'gender':'string',

  //     };
  //     http.Response response =
  //         await http.post(url, body: jsonEncode(body), headers: headers);
  //     if (response.statusCode == 200) {
  //       final json = jsonDecode(response.body);
  //       if (json['status'] == 'success') {
  //         var data = json['data'];
  //         var user = data['user'];
  //         return user;
  //       } else if (json['code'] == 1) {
  //         throw jsonDecode(response.body)['message'];
  //       }
  //     } else {
  //       throw jsonDecode(response.body)["Message"] ?? "Unknown Error Occurred";
  //     }
  //   } catch (error) {
  //     rethrow;
  //   }
  // }

  // Future<List<User>> getListUserbyAdmin(String token) async {
  //   var headers = {
  //     'Content-Type': 'application/json',
  //     'Authorization': 'Bearer $token',
  //   };
  //   try {
  //     var url = Uri.parse(
  //         ApiEndPoints.baseUrl + ApiEndPoints.adminEndpoints.listUserbyAdmin);
  //     final http.Response response = await http.get(url, headers: headers);

  //     if (response.statusCode == 202) {
  //       final dynamic jsonResponse = jsonDecode(response.body);
  //     // print(jsonResponse);
  //       if (jsonResponse['status'] == 'success') {
  //         final List<dynamic> userDataList = jsonResponse['data'];
    
  //         final List<User> users = userDataList
  //             .map(
  //                 (userData) => User.fromJson(userData as Map<String, dynamic>))
  //             .toList();
          
  //         return users;
  //       } else {
  //         // Use the null-aware operator to check for a message
  //         throw jsonResponse['message'] ?? 'Unknown Error Occurred';
  //       }
  //     } else {
  //       final dynamic errorResponse = jsonDecode(response.body);
  //       // Use the null-aware operator to check for a Message property
  //       throw errorResponse?['Message'] ?? 'Unknown Error Occurred';
  //     }
  //   } catch (error) {
  //     throw error.toString(); // Convert the error to a string
  //   }
  // }

  // Future<bool> changeUserStatusById(String token, int userId) async {
  //   var headers = {
  //     'Content-Type': 'application/json',
  //     'Authorization': 'Bearer $token',
  //   };

  //   var url = Uri.parse(
  //       '${ApiEndPoints.baseUrl}${ApiEndPoints.adminEndpoints.updateStatusAccount}?id=$userId');
  //   var data = {
  //     'id': userId,
  //   };
  //   // print(url);
  //   try {
  //     final http.Response response = await http.put(
  //       url,
  //       headers: headers,
  //       body: jsonEncode(data),
  //     );

  //     if (response.statusCode == 202) {
  //       final dynamic jsonResponse = jsonDecode(response.body);

  //       if (jsonResponse['status'] == 'success') {
  //         // Thay đổi trạng thái thành công
  //         return true;
  //       } else {
  //         throw jsonResponse['message'] ?? 'Failed to change status';
  //       }
  //     } else {
  //       final dynamic errorResponse = jsonDecode(response.body);
  //       throw errorResponse?['message'] ?? 'Unknown Error Occurred';
  //     }
  //   } catch (error) {
  //     throw error.toString();
  //   }
  // }

  // Future<bool> deleteUserAddress(
  //     String token, int userId, int userAddressId) async {
  //   var headers = {
  //     'Content-Type': 'application/json',
  //     'Authorization': 'Bearer $token',
  //   };

  //   var url = Uri.parse(
  //       '${ApiEndPoints.baseUrl}${ApiEndPoints.adminEndpoints.deleteAddressAccount}?id=$userId&idAddress=$userAddressId');
  //   var data = {'id': userId, 'idAddress': userAddressId};
  //   // print(url);
  //   try {
  //     final http.Response response = await http.delete(
  //       url,
  //       headers: headers,
  //       body: jsonEncode(data),
  //     );

  //     if (response.statusCode == 202) {
  //       final dynamic jsonResponse = jsonDecode(response.body);

  //       if (jsonResponse['status'] == 'success') {
  //         // Thay đổi trạng thái thành công
  //         return true;
  //       } else {
  //         throw jsonResponse['message'] ?? 'Failed to Delete status';
  //       }
  //     } else {
  //       final dynamic errorResponse = jsonDecode(response.body);
  //       throw errorResponse?['message'] ?? 'Unknown Error Occurred';
  //     }
  //   } catch (error) {
  //     throw error.toString();
  //   }
  // }

}
