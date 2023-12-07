import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:workshop_mobi/api/api_service.dart';
import 'package:workshop_mobi/model/student/workshop_endroll.dart';
import 'package:workshop_mobi/screens/userLayout/widgets/detail_worskshop.dart';
import 'package:workshop_mobi/screens/userLayout/widgets/my_schedule.dart';

class ManagePage extends StatefulWidget {
  final String? token;

  ManagePage({required this.token, Key? key}) : super(key: key);

  @override
  State<ManagePage> createState() => _ManagePageState();
}

class _ManagePageState extends State<ManagePage> {
  double targetHeightFactor = 0.06;
  late Future<String?> userName;

  @override
  void initState() {
    super.initState();
    userName = _getUserName();
  }

  Future<String?> _getUserName() async {
    const FlutterSecureStorage storage = FlutterSecureStorage();
    String? userName = await storage.read(key: 'userName');
    return userName;
  }

  double convertHeight(double height) {
    return MediaQuery.of(context).size.height * height;
  }

  double convertWidth(double width) {
    return MediaQuery.of(context).size.width * width;
  }

  Future<List<workshopEndrollResponses>> fetchWorkshopEndroll(String token) async {
    try {
      if (token.isNotEmpty) {
        final apiService = ApiService();
        final workshopEndroll = await apiService.listWorkShopByStudent(token);
        return workshopEndroll ?? [];
      } else {
        return [];
      }
    } catch (e) {
      print('Error fetching workshopEndroll: $e');
      return [];
    }
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        children: [
          Padding(
            padding: EdgeInsets.symmetric(
              vertical: convertHeight(0.00),
              horizontal: convertWidth(0.025),
            ),
          ),
          Padding(
            padding: EdgeInsets.symmetric(
              vertical: convertHeight(0.01),
              horizontal: convertWidth(0.025),
            ),
            child: Column(
              children: [
                const Row(
                  children: [
                    Text(
                      'Your Bought Workshops',
                    ),
                  ],
                ),
                SizedBox(
                  height: convertHeight(0.015),
                ),
                FutureBuilder<List<workshopEndrollResponses>>(
                  future: fetchWorkshopEndroll(widget.token ?? ''),
                  builder: (context, snapshot) {
                    if (snapshot.connectionState == ConnectionState.waiting) {
                      return const CircularProgressIndicator();
                    } else if (snapshot.hasError) {
                      return const Text('Error fetching workshopEndroll');
                    } else {
                      return SingleChildScrollView(
                        scrollDirection: Axis.horizontal,
                        child: Row(
                          children: [
                            for (var workshop in snapshot.data ?? [])
                              Padding(
                                padding: EdgeInsets.symmetric(
                                  horizontal: convertWidth(0.015),
                                ),
                                child: DetailWorkshop(workshopEndroll: workshop),
                              ),
                          ],
                        ),
                      );
                    }
                  },
                ),
              ],
            ),
          ),
          Padding(
            padding: EdgeInsets.symmetric(
              horizontal: convertWidth(0.025),
              vertical: convertHeight(0.000),
            ),
            child: Column(
              children: [
                const Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    Text(
                      'Your schedule',
                    ),
                  ],
                ),
                SingleChildScrollView(
                  scrollDirection: Axis.horizontal,
                  child: Row(
                    children: [
                      Padding(
                        padding: EdgeInsets.symmetric(
                          horizontal: convertWidth(0.015),
                        ),
                        child: const MyStudentSchedule(),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
