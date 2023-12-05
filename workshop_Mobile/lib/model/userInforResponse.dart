// ignore_for_file: non_constant_identifier_names, file_names
import 'package:json_annotation/json_annotation.dart';
part 'userInforResponse.g.dart';

@JsonSerializable()
class UserInfoResponse {
  final int id;
  final String full_name;
  final String user_name;
  final String email;
  final String phoneNumber;
  final String? image_url;
  final double balance;
  final String gender;
  final List<String> roles;
  final bool? isEnable;
  final List<UserAddress> userAddresses;
  final List<UserBank> userBanks;

  UserInfoResponse({
    required this.id,
    required this.full_name,
    required this.user_name,
    required this.email,
    required this.phoneNumber,
    required this.image_url,
    required this.balance,
    required this.gender,
    required this.roles,
    required this.isEnable,
    required this.userAddresses,
    required this.userBanks,
  });

  factory UserInfoResponse.fromJson(Map<String, dynamic> json) {
    return UserInfoResponse(
      id: json['id'] as int,
      full_name: json['full_name'] as String,
      user_name: json['user_name'] as String,
      email: json['email'] as String,
      phoneNumber: json['phoneNumber'] as String,
      image_url: json['image_url'] as String?,
      balance: (json['balance'] as num).toDouble(),
      gender: json['gender'] as String,
      roles: (json['roles'] as List<dynamic>).map((e) => e as String).toList(),
      isEnable: json['isEnable'] as bool?,
      userAddresses: (json['userAddresses'] as List<dynamic>?)
              ?.map((e) => UserAddress.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
      userBanks: (json['userBanks'] as List<dynamic>?)
              ?.map((e) => UserBank.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
    );
  }
}

@JsonSerializable()
class UserAddress {
  final int id;
  final String Address;
  final String City;
  final String State;
  final int PostalCode;

  UserAddress({
    required this.id,
    required this.Address,
    required this.City,
    required this.State,
    required this.PostalCode,
  });

  factory UserAddress.fromJson(Map<String, dynamic> json) {
    return UserAddress(
      id: json['id'] as int,
      Address: json['Address'] as String? ?? '',
      City: json['City'] as String? ?? '',
      State: json['State'] as String? ?? '',
      PostalCode: json['PostalCode'] as int? ?? 0,
    );
  }
}

@JsonSerializable()
class UserBank {
  final int id;
  final String bankName;
  final String bankAccount;

  UserBank({
    required this.id,
    required this.bankName,
    required this.bankAccount,
  });

  factory UserBank.fromJson(Map<String, dynamic> json) {
    return UserBank(
      id: json['id'] as int,
      bankName: json['bankName'] as String? ?? '',
      bankAccount: json['bankAccount'] as String? ?? '',
    );
  }
}
