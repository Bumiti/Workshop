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

   factory UserInfoResponse.fromJson(Map<String, dynamic> json) => _$UserInfoResponseFromJson(json);
  Map<String, dynamic> toJson() => _$UserInfoResponseToJson(this);
}

@JsonSerializable()
class UserAddress {
  final int id;
  final String address;
  final String city;
  final String state;
  final int postalCode;

  UserAddress({
    required this.id,
    required this.address,
    required this.city,
    required this.state,
    required this.postalCode,
  });

  factory UserAddress.fromJson(Map<String, dynamic> json) =>
      _$UserAddressFromJson(json);
  Map<String, dynamic> toJson() => _$UserAddressToJson(this);
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

  factory UserBank.fromJson(Map<String, dynamic> json) =>
      _$UserBankFromJson(json);
  Map<String, dynamic> toJson() => _$UserBankToJson(this);
}
