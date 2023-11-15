import 'package:flutter/material.dart';
import 'package:workshop_mobi/api/api_service.dart';
import 'package:workshop_mobi/model/user.dart';
import 'package:shared_preferences/shared_preferences.dart';

class UserDetailScreen extends StatefulWidget {
  final User user;

  UserDetailScreen({required this.user});

  @override
  _UserDetailScreenState createState() => _UserDetailScreenState();
}

class _UserDetailScreenState extends State<UserDetailScreen> {
  final ApiService _apiSer = ApiService();
  late Future<SharedPreferences> _prefs;

  @override
  void initState() {
    super.initState();
    _prefs = SharedPreferences.getInstance();
  }

  Future<void> _deleteUserAddress(int userAddressId) async {
    final SharedPreferences prefs = await _prefs;
    final token = prefs.get('token').toString();
    final userId = widget.user.id;

    try {
      await _apiSer.deleteUserAddress(token, userId, userAddressId);

      // Xóa thành công, cập nhật danh sách địa chỉ của người dùng
      setState(() {
        widget.user.userAddresses.removeWhere((address) => address.id == userAddressId);
      });
    } catch (error) {
      // Xử lý lỗi hoặc hiển thị thông báo lỗi
      print('Error: $error');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('User Detail')),
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Center(
              child: Text(
                'User Detail:',
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
            ),
            Card(
              child: ListTile(
                title: const Text('User Id'),
                subtitle: Text(widget.user.id.toString()),
              ),
            ),
            Card(
              child: ListTile(
                title: const Text('Full Name'),
                subtitle: Text(widget.user.fullName),
              ),
            ),
            Card(
              child: ListTile(
                title: const Text('User Name'),
                subtitle: Text(widget.user.userName),
              ),
            ),
            Card(
              child: ListTile(
                title: const Text('Phone Number'),
                subtitle: Text(widget.user.phoneNumber),
              ),
            ),
            const Divider(),
            const Center(
              child: Text(
                'Addresses:',
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
            ),
            if (widget.user.userAddresses.isNotEmpty)
              Column(
                children: widget.user.userAddresses
                    .map((address) => Card(
                          child: Column(
                            children: [
                              ListTile(
                                title: const Text('State'),
                                subtitle: Text(address.state),
                              ),
                              ListTile(
                                title: const Text('Address'),
                                subtitle: Text(address.address),
                              ),
                              ListTile(
                                title: const Text('Postal Code'),
                                subtitle: Text(address.postalCode.toString()),
                              ),
                              ListTile(
                                title: const Text('City'),
                                subtitle: Text(address.city),
                              ),
                              IconButton(
                                icon: const Icon(Icons.delete),
                                onPressed: () async {
                                  _deleteUserAddress(address.id);
                                },
                              ),
                            ],
                          ),
                        ))
                    .toList(),
              )
            else
              Center(
                child: Column(
                  children: [
                    const Text('No addresses available.'),
                    ElevatedButton(
                      onPressed: () {
                        showDialog(
                          context: context,
                          builder: (BuildContext context) {
                            return const AlertDialog(
                              title: Text('Add Address'),
                              // ...
                            );
                          },
                        );
                      },
                      child: Text('Add Address'),
                    ),
                  ],
                ),
              ),
          ],
        ),
      ),
    );
  }
}
