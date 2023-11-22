class ApiEndPoints {
  static const String baseUrl = 'http://192.168.1.3:8089/';
  // ignore: library_private_types_in_public_api
  static  _AuthEndPoints authEndpoints = _AuthEndPoints();
  // ignore: library_private_types_in_public_api
  static _AdminEndPoints adminEndpoints = _AdminEndPoints();

}

class _AuthEndPoints {
  final String register = 'auth/user/register';
  final String loginWebAccount = 'auth/loginWeb';

 

}
class _AdminEndPoints{
 final String listUserbyAdmin = 'admin/user/listUser';
  final String updateStatusAccount= 'admin/user/changeStatus';
  final String deleteAddressAccount= 'admin/user/deleteAddress';
}
