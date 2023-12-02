import 'dart:convert';
import 'package:workshop_mobi/model/userInforResponse.dart';
import 'package:workshop_mobi/utils/api_endpoints.dart';
import 'package:http/http.dart' as http;

class ApiService {
  ApiService();
  Future<dynamic> loginWebAccount(String email, String password) async {
    var headers = {'Content-Type': 'application/json'};
    try {
      var url = Uri.parse(
          ApiEndPoints.baseUrl + ApiEndPoints.authEndpoints.loginWebAccount);
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

  Future<dynamic> login0AuthenAccount(String email) async {
    var headers = {'Content-Type': 'application/json'};
    try {
      var url = Uri.parse(
          ApiEndPoints.baseUrl + ApiEndPoints.authEndpoints.loginOAuthen);
      Map body = {
        'email': email.trim(),
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

  Future<dynamic> registerAccount(
      String email, String password, String roles) async {
    var headers = {'Content-Type': 'application/json'};
    try {
      var url =
          Uri.parse(ApiEndPoints.baseUrl + ApiEndPoints.authEndpoints.register);
      Map body = {
        'full_name': email.trim(),
        'balance': 0,
        'user_name': email.trim(),
        'email': email.trim(),
        'password': password,
        'phoneNumber': 'string',
        'gender': 'string',
        'role': roles,
        "enable": true
      };
      http.Response response =
          await http.post(url, body: jsonEncode(body), headers: headers);

      if (response.statusCode == 202) {
        final json = jsonDecode(response.body);
        return json;
      } else if (response.statusCode == 302) {
        final json = jsonDecode(response.body);
        return json;
      } else {
        throw jsonDecode(response.body)["Message"] ?? "Unknown Error Occurred";
      }
    } catch (error) {
      rethrow;
    }
  }

  Future<dynamic> resetPassword(String email) async {
    var headers = {
      'Content-Type': 'application/json',
    };
    var url = Uri.parse(
        '${ApiEndPoints.baseUrl}${ApiEndPoints.authEndpoints.resetPassword}?Email=$email');
    var data = {
      'Email': email,
    };

    try {
      final http.Response response = await http.post(
        url,
        headers: headers,
        body: jsonEncode(data),
      );

      if (response.statusCode == 202) {
        final jsonResponse = jsonDecode(response.body);

        if (jsonResponse['status'] == 'Success') {
          return jsonResponse;
        } else {
          throw jsonResponse['message'] ?? 'Failed to Reset Password ';
        }
      } else {
        final dynamic errorResponse = jsonDecode(response.body);
        throw errorResponse?['message'] ?? 'Unknown Error Occurred';
      }
    } catch (error) {
      throw error.toString();
    }
  }

  Future<UserInfoResponse> getinforStudent(String token) async {
    var headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token',
    };
    try {
      var url = Uri.parse(
          ApiEndPoints.baseUrl + ApiEndPoints.studentEndPoints.studentInfo);
      final http.Response response = await http.get(url, headers: headers);
 
      if (response.statusCode == 202) {
        final dynamic jsonResponse = jsonDecode(response.body);
        
        if (jsonResponse['status'] == 'Success') {
          final Map<String, dynamic> userData = jsonResponse['data'];
           print(userData);
          final UserInfoResponse user = UserInfoResponse.fromJson(userData);
         
          return user;
        } else {
          // Use the null-aware operator to check for a message
          throw jsonResponse['message'] ?? 'Unknown Error Occurred';
        }
      } else {
        final dynamic errorResponse = jsonDecode(response.body);
        // Use the null-aware operator to check for a Message property
        throw errorResponse?['Message'] ?? 'Unknown Error Occurred';
      }
    } catch (error) {
      throw error.toString(); // Convert the error to a string
    }
  }

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
