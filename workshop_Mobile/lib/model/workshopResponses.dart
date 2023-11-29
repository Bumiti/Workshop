// ignore_for_file: non_constant_identifier_names, file_names

import 'package:json_annotation/json_annotation.dart';
 part 'workshopResponses.g.dart';
// models/workshop_data.dart
@JsonSerializable()
class WorkshopData {
  String workshopName;
  String description;
  double price;
  String startDate;
  String endDate;
  int student_count;
  String type;
  List<MediaInfo> mediaInfoList;
  List<DiscountDTO> discountDTOs;
  List<WorkshopLocation> workshopLocations;

  WorkshopData({
    required this.workshopName,
    required this.description,
    required this.price,
    required this.startDate,
    required this.endDate,
    required this.student_count,
    required this.type,
    required this.mediaInfoList,
    required this.discountDTOs,
    required this.workshopLocations,
  });
  factory WorkshopData.fromJson(Map<String, dynamic> json) => _$WorkshopDataFromJson(json);
  Map<String, dynamic> toJson() => _$WorkshopDataToJson(this);
}

// models/media_info.dart
@JsonSerializable()
class MediaInfo {
  int id;
  String thumbnailSrc;
  String title;
  String urlImage;
  String urlMedia;

  MediaInfo({
    required this.id,
    required this.thumbnailSrc,
    required this.title,
    required this.urlImage,
    required this.urlMedia,
  });
   factory MediaInfo.fromJson(Map<String, dynamic> json) =>
      _$MediaInfoFromJson(json);
  Map<String, dynamic> toJson() => _$MediaInfoToJson(this);
}

// models/discount_dto.dart\
@JsonSerializable()
class DiscountDTO {
  int courseDiscountId;
  int quantity;
  // DateTime redemptionDate;
  double valueDiscount;
  String name;
  String description;
  int remainingUses;

  DiscountDTO({
    required this.courseDiscountId,
    required this.quantity,
    // required this.redemptionDate,
    required this.valueDiscount,
    required this.name,
    required this.description,
    required this.remainingUses,
  });
  factory DiscountDTO.fromJson(Map<String, dynamic> json) =>
      _$DiscountDTOFromJson(json);
  Map<String, dynamic> toJson() => _$DiscountDTOToJson(this);
}

// models/workshop_location.dart
@JsonSerializable()
class WorkshopLocation {
  int workshopLocationId;
  String schedule_Date;
  String area;

  WorkshopLocation({
    required this.workshopLocationId,
    required this.schedule_Date,
    required this.area,
  });
  factory WorkshopLocation.fromJson(Map<String, dynamic> json) =>
      _$WorkshopLocationFromJson(json);
  Map<String, dynamic> toJson() => _$WorkshopLocationToJson(this);
}


