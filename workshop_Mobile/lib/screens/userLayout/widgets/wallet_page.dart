import 'package:flutter/material.dart';
import 'package:workshop_mobi/api/api_service.dart';
import 'package:workshop_mobi/model/student/wallet.dart';
import 'package:workshop_mobi/screens/userLayout/widgets/last_transaction.dart';

class StudentWalletPage extends StatefulWidget {
  final String? token;

  const StudentWalletPage({Key? key, required this.token});

  @override
  State<StudentWalletPage> createState() => _StudentWalletPageState();
}

class _StudentWalletPageState extends State<StudentWalletPage> {
  late Future<walletResponses?> _walletFuture;

  @override
  void initState() {
    super.initState();
    _walletFuture = fetchWalletResponses(widget.token!);
  }

  Future<walletResponses?> fetchWalletResponses(String token) async {
    try {
      if (token.isNotEmpty) {
        final apiService = ApiService();
        final wallet = await apiService.walletStudent(token);
        return wallet;
      } else {
        return null;
      }
    } catch (e) {
      print('Error fetching walletResponses: $e');
      return null;
    }
  }

  @override
  Widget build(BuildContext context) {
    double baseWidth = 430;
    double fem = MediaQuery.of(context).size.width / baseWidth;
    double ffem = fem * 0.97;

    return FutureBuilder<walletResponses?>(
      future: _walletFuture,
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          // Loading state
          return Center(child: CircularProgressIndicator());
        } else if (snapshot.hasError) {
          // Error state
          return Center(child: Text('Error loading wallet: ${snapshot.error}'));
        } else {
          // Data loaded successfully
          walletResponses? wallet = snapshot.data;

          return Scaffold(
            body: Container(
              width: double.infinity,
              height: 770 * fem,
              decoration: const BoxDecoration(
                color: Color(0xffffffff),
              ),
              child: Stack(
                children: [
                  const Background(),
                  Container(
                    width: double.infinity,
                    height: double.infinity,
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Column(
                          children: [
                            Container(
                              padding: EdgeInsets.fromLTRB(
                                  19 * fem, 78 * fem, 32 * fem, 555 * fem),
                              width: MediaQuery.of(context).size.width,
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Container(
                                    margin: EdgeInsets.fromLTRB(
                                        0 * fem, 0 * fem, 0 * fem, 10 * fem),
                                    height: 27 * fem,
                                    child: Row(
                                      crossAxisAlignment:
                                          CrossAxisAlignment.start,
                                      children: [
                                        Text(
                                          'Balance',
                                          style: TextStyle(
                                            fontFamily: 'Readex Pro',
                                            fontSize: 22 * ffem,
                                            fontWeight: FontWeight.w500,
                                            height: 1.3 * ffem / fem,
                                            letterSpacing: 0.0200000003 * fem,
                                            color: Color(0xff0f172a),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                  Container(
                                    margin: EdgeInsets.fromLTRB(
                                        0 * fem, 0 * fem, 0 * fem, 28 * fem),
                                    height: 45 * fem,
                                    child: Row(
                                      crossAxisAlignment:
                                          CrossAxisAlignment.start,
                                      children: [
                                        Expanded(
                                          child: Container(
                                            height: double.infinity,
                                            child: Text(
                                              '${wallet?.current_balance ?? 0}',
                                              style: TextStyle(
                                                fontFamily: 'Readex Pro',
                                                fontSize: 42 * ffem,
                                                fontWeight: FontWeight.bold,
                                                height: 0.8125 * ffem / fem,
                                                letterSpacing:
                                                    0.0320000005 * fem,
                                                color: Color(0xff0f172a),
                                              ),
                                            ),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                  Container(
                                    padding: const EdgeInsets.all(2.0),
                                    margin: const EdgeInsets.only(
                                      left: 0,
                                      right: 25,
                                      top: 0,
                                    ),
                                    child: ElevatedButton(
                                      onPressed: () {
                                        print("gọi hàm paypal");
                                      },
                                      style: ElevatedButton.styleFrom(
                                        backgroundColor: const Color.fromARGB(
                                            249,
                                            255,
                                            255,
                                            255), // Màu nền của nút
                                        minimumSize: const Size(0, 30),
                                      ),
                                      child: const Text(
                                        'Deposit Now',
                                        style: TextStyle(
                                          fontSize: 16,
                                          fontWeight: FontWeight.w600,
                                          color: Color.fromARGB(
                                              255, 0, 0, 0), // Màu chữ của nút
                                        ),
                                      ),
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                  LastTransaction(
                    fem: fem,
                    ffem: ffem,
                    wallet: wallet ??
                        walletResponses(current_balance: 0, transactions: []),
                  ),
                ],
              ),
            ),
          );
        }
      },
    );
  }
}

class Background extends StatelessWidget {
  const Background({
    Key? key,
  });

  @override
  Widget build(BuildContext context) {
    return Positioned(
      left: 0,
      top: 0,
      bottom: 0,
      child: Container(
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment(0, -1),
            end: Alignment(0, 1),
            colors: <Color>[
              Color(0xff27aae1),
              Color(0xcf9fc9ed),
              Color(0xbea6cdef),
              Color(0xa4b2d4f1),
              Color(0x7ac6dff4),
              Color(0x3a9e9e9e),
            ],
            stops: <double>[0, 0.188, 0.251, 0.354, 0.521, 1],
          ),
        ),
        child: Image.asset(
          'lib/assets/Ellipse1248.png',
          height: MediaQuery.of(context).size.height * 0.5,
          fit: BoxFit.contain,
        ),
      ),
    );
  }
}
