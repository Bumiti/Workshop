// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'workshopResponses.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

WorkshopData _$WorkshopDataFromJson(Map<String, dynamic> json) => WorkshopData(
      workshopName: json['workshopName'] as String,
      description: json['description'] as String,
      price: (json['price'] as num).toDouble(),
      startDate: json['startDate'] as String,
      endDate: json['endDate'] as String,
      student_count: json['student_count'] as int,
      type: json['type'] as String,
      mediaInfoList: (json['mediaInfoList'] as List<dynamic>)
          .map((e) => MediaInfo.fromJson(e as Map<String, dynamic>))
          .toList(),
      discountDTOs: (json['discountDTOs'] as List<dynamic>)
          .map((e) => DiscountDTO.fromJson(e as Map<String, dynamic>))
          .toList(),
      workshopLocations: (json['workshopLocations'] as List<dynamic>)
          .map((e) => WorkshopLocation.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$WorkshopDataToJson(WorkshopData instance) =>
    <String, dynamic>{
      'workshopName': instance.workshopName,
      'description': instance.description,
      'price': instance.price,
      'startDate': instance.startDate,
      'endDate': instance.endDate,
      'student_count': instance.student_count,
      'type': instance.type,
      'mediaInfoList': instance.mediaInfoList,
      'discountDTOs': instance.discountDTOs,
      'workshopLocations': instance.workshopLocations,
    };

MediaInfo _$MediaInfoFromJson(Map<String, dynamic> json) => MediaInfo(
      id: json['id'] as int,
      thumbnailSrc: json['thumbnailSrc'] as String,
      title: json['title'] as String,
      urlImage: json['urlImage'] as String,
      urlMedia: json['urlMedia'] as String,
    );

Map<String, dynamic> _$MediaInfoToJson(MediaInfo instance) => <String, dynamic>{
      'id': instance.id,
      'thumbnailSrc': instance.thumbnailSrc,
      'title': instance.title,
      'urlImage': instance.urlImage,
      'urlMedia': instance.urlMedia,
    };

DiscountDTO _$DiscountDTOFromJson(Map<String, dynamic> json) => DiscountDTO(
      courseDiscountId: json['courseDiscountId'] as int,
      quantity: json['quantity'] as int,
      valueDiscount: (json['valueDiscount'] as num).toDouble(),
      name: json['name'] as String,
      description: json['description'] as String,
      remainingUses: json['remainingUses'] as int,
    );

Map<String, dynamic> _$DiscountDTOToJson(DiscountDTO instance) =>
    <String, dynamic>{
      'courseDiscountId': instance.courseDiscountId,
      'quantity': instance.quantity,
      'valueDiscount': instance.valueDiscount,
      'name': instance.name,
      'description': instance.description,
      'remainingUses': instance.remainingUses,
    };

WorkshopLocation _$WorkshopLocationFromJson(Map<String, dynamic> json) =>
    WorkshopLocation(
      workshopLocationId: json['workshopLocationId'] as int,
      schedule_Date: json['schedule_Date'] as String,
      area: json['area'] as String,
    );

Map<String, dynamic> _$WorkshopLocationToJson(WorkshopLocation instance) =>
    <String, dynamic>{
      'workshopLocationId': instance.workshopLocationId,
      'schedule_Date': instance.schedule_Date,
      'area': instance.area,
    };
