// ignore_for_file: library_private_types_in_public_api

class ApiEndPoints {
  static const String baseUrl = 'http://192.168.1.10:8089/';
  
  static _AuthEndPoints authEndpoints = _AuthEndPoints();
  static _TeacherEndPoints teacherEndPoints = _TeacherEndPoints();
  static _HomePageEndPoints homePageEndPoints = _HomePageEndPoints();
  static _StudentEndPoints studentEndPoints = _StudentEndPoints();



}

class _AuthEndPoints {
  final String register = 'auth/user/register';
  final String loginWebAccount = 'auth/loginWeb';
  final String loginOAuthen = 'auth/loginOAuthentication';
  final String resetPassword = 'auth/user/forgetPassword';
}
// class _AdminEndPoints {
//   final String listUserbyAdmin = 'admin/user/listUser';
//   final String updateStatusAccount = 'admin/user/changeStatus';
//   final String deleteAddressAccount = 'admin/user/deleteAddress';
// }
class _HomePageEndPoints {
  final String listWorkshop = 'web/course/list';
  final String workShopById = 'web/course/detail';
  final String checkUserInCourse = 'web/course/checkedUser';
}
class _StudentEndPoints {
   final String studentInfo = 'user/detail';
   final String studentByWorkshop = 'user/byCourse';
}
class _TeacherEndPoints {
  final String listWorshop = 'seller/course/list';
  final String addWorshop = 'seller/course/add';
  final String editWorshop = 'seller/course/update';
  final String deleteWorshop = 'seller/course/delete';
  final String sendDiscountToListStudent = 'seller/course/addListStudent';
  final String teacherDetail = 'seller/detail';
  final String teacherDetailEdit = 'seller/edit';
  final String deleteAddressAccount = 'seller/deleteAddress';
  final String listStudentInWorkshop = 'seller/course/listStudent';
  final String deposit = 'seller/deposit';
}
