// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'userInforResponse.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

UserInfoResponse _$UserInfoResponseFromJson(Map<String, dynamic> json) =>
    UserInfoResponse(
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
      userAddresses: (json['userAddresses'] as List<dynamic>)
          .map((e) => UserAddress.fromJson(e as Map<String, dynamic>))
          .toList(),
      userBanks: (json['userBanks'] as List<dynamic>)
          .map((e) => UserBank.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$UserInfoResponseToJson(UserInfoResponse instance) =>
    <String, dynamic>{
      'id': instance.id,
      'full_name': instance.full_name,
      'user_name': instance.user_name,
      'email': instance.email,
      'phoneNumber': instance.phoneNumber,
      'image_url': instance.image_url,
      'balance': instance.balance,
      'gender': instance.gender,
      'roles': instance.roles,
      'isEnable': instance.isEnable,
      'userAddresses': instance.userAddresses,
      'userBanks': instance.userBanks,
    };

UserAddress _$UserAddressFromJson(Map<String, dynamic> json) => UserAddress(
      id: json['id'] as int,
      Address: json['Address'] as String,
      City: json['City'] as String,
      State: json['State'] as String,
      PostalCode: json['PostalCode'] as int,
    );

Map<String, dynamic> _$UserAddressToJson(UserAddress instance) =>
    <String, dynamic>{
      'id': instance.id,
      'Address': instance.Address,
      'City': instance.City,
      'State': instance.State,
      'PostalCode': instance.PostalCode,
    };

UserBank _$UserBankFromJson(Map<String, dynamic> json) => UserBank(
      id: json['id'] as int,
      bankName: json['bankName'] as String,
      bankAccount: json['bankAccount'] as String,
    );

Map<String, dynamic> _$UserBankToJson(UserBank instance) => <String, dynamic>{
      'id': instance.id,
      'bankName': instance.bankName,
      'bankAccount': instance.bankAccount,
    };
