import 'package:json_annotation/json_annotation.dart';

part 'user.g.dart';

@JsonSerializable()
class User {
  final int id;
  final String fullName;
  final String userName;
  final String email;
  final String phoneNumber;
  final String gender;
  final List<String> roles;
  final bool enable;
  final List<UserAddress> userAddresses;

  User({
    required this.id,
    required this.fullName,
    required this.userName,
    required this.email,
    required this.phoneNumber,
    required this.gender,
    required this.roles,
    required this.enable,
    required this.userAddresses,
  });

  factory User.fromJson(Map<String, dynamic> json) => _$UserFromJson(json);
  Map<String, dynamic> toJson() => _$UserToJson(this);
}

@JsonSerializable()
class UserAddress {
   final int id;
  final String state;
  final String address;
  final int postalCode;
  final String city;

  UserAddress({
    required this.id,
    required this.state,
    required this.address,
    required this.postalCode,
    required this.city,
  });

  factory UserAddress.fromJson(Map<String, dynamic> json) =>
      _$UserAddressFromJson(json);
  Map<String, dynamic> toJson() => _$UserAddressToJson(this);
}
