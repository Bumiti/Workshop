// ignore_for_file: file_names, non_constant_identifier_names

import 'package:json_annotation/json_annotation.dart';

part 'workshopUpdateRequest.g.dart';
@JsonSerializable()
class CourseUpdateRequest {
  @JsonKey(required: true)
  late String name;

  @JsonKey(required: true)
  late String description;

  @JsonKey(required: true)
  late double price;

  @JsonKey(required: true)
  late DateTime startDate;

  @JsonKey(required: true)
  late DateTime endDate;

  @JsonKey(required: true)
  late int student_count;

  @JsonKey(required: true)
  late String type;

  @JsonKey(name: 'courseMediaInfos')
  late List<CourseMediaInfoDTO> mediaInfoList;

  @JsonKey(name: 'discountDTOS')
  late List<DiscountDTO> discountDTOS;

  @JsonKey(name: 'courseLocations')
  late List<CourseLocationDTO> courseLocation;

  CourseUpdateRequest({
    required this.name,
    required this.description,
    required this.price,
    required this.startDate,
    required this.endDate,
    required this.student_count,
    required this.type,
    required this.mediaInfoList,
    required this.discountDTOS,
    required this.courseLocation,
  });

  factory CourseUpdateRequest.fromJson(Map<String, dynamic> json) =>
      _$CourseUpdateRequestFromJson(json);

  Map<String, dynamic> toJson() => _$CourseUpdateRequestToJson(this);
}

@JsonSerializable()
class CourseMediaInfoDTO {
  late int id;
  late String urlMedia;
  late String urlImage;
  late String thumbnailSrc;
  late String title;

  CourseMediaInfoDTO({
    required this.id,
    required this.urlMedia,
    required this.urlImage,
    required this.thumbnailSrc,
    required this.title,
  });

  factory CourseMediaInfoDTO.fromJson(Map<String, dynamic> json) =>
      _$CourseMediaInfoDTOFromJson(json);

  Map<String, dynamic> toJson() => _$CourseMediaInfoDTOToJson(this);
}

@JsonSerializable()
class DiscountDTO {
  late int id;
  late int quantity;
  late DateTime redemptionDate;
  late int valueDiscount;
  late String name;
  late String description;
  late int remainingUses;

  DiscountDTO({
    required this.id,
    required this.quantity,
    required this.redemptionDate,
    required this.valueDiscount,
    required this.name,
    required this.description,
    required this.remainingUses,
  });

  factory DiscountDTO.fromJson(Map<String, dynamic> json) =>
      _$DiscountDTOFromJson(json);

  Map<String, dynamic> toJson() => _$DiscountDTOToJson(this);
}

@JsonSerializable()
class CourseLocationDTO {
  late int id;
  late String Area;
  late DateTime schedule_Date;

  CourseLocationDTO({
    required this.id,
    required this.Area,
    required this.schedule_Date,
  });

  factory CourseLocationDTO.fromJson(Map<String, dynamic> json) =>
      _$CourseLocationDTOFromJson(json);

  Map<String, dynamic> toJson() => _$CourseLocationDTOToJson(this);
}
