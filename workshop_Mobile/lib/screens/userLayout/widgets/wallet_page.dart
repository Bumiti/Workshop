import 'package:flutter/material.dart';
import 'package:flutter/gestures.dart';
import 'dart:ui';
import 'package:google_fonts/google_fonts.dart';
// import 'package:myapp/utils.dart';

class StudentWalletPage extends StatelessWidget {
  const StudentWalletPage({Key? key});
  @override
  Widget build(BuildContext context) {
    double baseWidth = 430;
    double fem = MediaQuery.of(context).size.width / baseWidth;
    double ffem = fem * 0.97;
    return Container(
      width: double.infinity,
      child: Container(
        // detailQZs (756:1818)
        width: double.infinity,
        height: 770 * fem,
        decoration: BoxDecoration(
          color: Color(0xffffffff),
        ),
        child: Stack(
          children: [
            // Ellipse3 image
            Positioned(
              left: 0,
              top: 0,
              bottom: 0,
              child: Container(
                decoration: BoxDecoration(
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
            ),
            Container(
              // wJu (70173283)
              width: double.infinity,
              height: double.infinity,
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Column(
                    children: [
                      Container(
                        // textboxV5X (756:1819)
                        padding: EdgeInsets.fromLTRB(
                            19 * fem, 78 * fem, 32 * fem, 555 * fem),
                        // width: double.infinity,
                        width: MediaQuery.of(context).size.width,

                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Container(
                              // navbarcomponentVUq (756:1890)
                              margin: EdgeInsets.fromLTRB(
                                  0 * fem, 0 * fem, 0 * fem, 10 * fem),
                              height: 27 * fem,
                              child: Row(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    // balanceR7b (756:1893)
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
                              // autogroupvpsfwLq (WBCPFnb61garrLo8jjvpsf)
                              margin: EdgeInsets.fromLTRB(
                                  0 * fem, 0 * fem, 0 * fem, 28 * fem),

                              height: 45 * fem,
                              child: Row(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Expanded(
                                    child: Container(
                                      // navbarcomponentT4H (756:1896)
                                      height: double.infinity,

                                      child: Text(
                                        '\$890,00',
                                        style: TextStyle(
                                          fontFamily: 'Readex Pro',
                                          fontSize: 42 * ffem,
                                          fontWeight: FontWeight.bold,
                                          height: 0.8125 * ffem / fem,
                                          letterSpacing: 0.0320000005 * fem,
                                          color: Color(0xff0f172a),
                                        ),
                                      ),
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            Container(
                              decoration: BoxDecoration(
                                color: Color.fromARGB(249, 255, 255, 255),
                                borderRadius: BorderRadius.circular(
                                    20.0), // Adjust the border radius
                              ),
                              child: Container(
                                padding: const EdgeInsets.all(8.0),
                                margin:
                                    const EdgeInsets.only(left: 25, right: 25),
                                child: Text(
                                  'Deposit Now',
                                  style: TextStyle(
                                    fontSize: 16,
                                    fontWeight: FontWeight.w600,
                                    color: Color.fromARGB(255, 0, 0, 0),
                                  ),
                                ),
                              ),
                            ),
                            // Align(
                            //   alignment: Alignment.topRight,
                            //   child: Container(
                            //     // illustrationshadowt37 (756:1908)
                            //     margin: EdgeInsets.fromLTRB(
                            //         0 * fem, 0 * fem, 4 * fem, 0 * fem),
                            //     width: MediaQuery.of(context).size.width *
                            //         0.3, // 30% chiều rộng màn hình
                            //     height:
                            //         MediaQuery.of(context).size.height * 0.2,
                            //     child: Align(
                            //       alignment: Alignment.centerRight,
                            //       child: Image.asset(
                            //         'lib/assets/Ellipse3.png',
                            //         width: double
                            //             .infinity, // Kích thước rộng tối đa
                            //         height: double
                            //             .infinity, // Kích thước cao tối đa
                            //         fit: BoxFit.cover,
                            //         // Giữ nguyên tỷ lệ và cắt bớt phần thừa
                            //       ),
                            //     ),
                            //   ),
                            // ),
                          ],
                        ),
                      ),
                    ],
                  ),
                  // Column(
                  //   children: [
                  //     Container(
                  //       // BuP (52066796)
                  //       margin: EdgeInsets.fromLTRB(
                  //           240 * fem, 0 * fem, 0 * fem, 400 * fem),
                  //       padding: EdgeInsets.fromLTRB(
                  //           0 * fem, 87 * fem, 0 * fem, 0 * fem),
                  //       width: 190 * fem,
                  //       height: 242 * fem,
                  //       decoration: BoxDecoration(
                  //         image: DecorationImage(
                  //           fit: BoxFit.cover,
                  //           image: AssetImage(
                  //             'lib/assets/other6.svg',
                  //           ),
                  //         ),
                  //       ),
                  //       child: Column(
                  //         crossAxisAlignment: CrossAxisAlignment.end,
                  //         children: [
                  //           Container(
                  //             // illustrationshadowt37 (756:1908)
                  //             margin: EdgeInsets.fromLTRB(
                  //                 0 * fem, 0 * fem, 4 * fem, 0 * fem),
                  //             width: 200 * fem,
                  //             height: 184 * fem,
                  //             child: Image.asset(
                  //               'lib/assets/illustrationshadowpng.png',
                  //               width: 200 * fem,
                  //               height: 184 * fem,
                  //             ),
                  //           ),
                  //         ],
                  //       ),
                  //     ),
                  //   ],
                  // ),
                ],
              ),
            ),

            Positioned(
              // group1171274831oQy (756:1820)
              left: 0 * fem,
              top: 322 * fem,
              child: Container(
                padding:
                    EdgeInsets.fromLTRB(0 * fem, 18 * fem, 0 * fem, 42 * fem),
                width: 430 * fem,
                height: 448 * fem,
                decoration: BoxDecoration(
                  color: Color(0xffffffff),
                  borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(50 * fem),
                    topRight: Radius.circular(50 * fem),
                  ),
                  boxShadow: [
                    BoxShadow(
                      color: Color(0x3f000000),
                      offset: Offset(0 * fem, 4 * fem),
                      blurRadius: 2 * fem,
                    ),
                  ],
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        // lasttransactionSD3 (756:1888)

                        Text(
                          'Last Transaction',
                          style: TextStyle(
                            fontFamily: 'Poppins',
                            fontSize: 20 * ffem,
                            fontWeight: FontWeight.w500,
                            height: 1.5 * ffem / fem,
                            color: Color(0xff191c32),
                          ),
                        ),
                      ],
                    ),
                    Row(
                      children: [
                        Container(
                          // frame1171276104YG5 (756:1847)

                          width: MediaQuery.of(context).size.width,
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: [
                              Container(
                                // listwithoutgraphlightneo4ER (756:1848)
                                margin: EdgeInsets.fromLTRB(
                                    0 * fem, 0 * fem, 0 * fem, 10 * fem),
                                padding: EdgeInsets.fromLTRB(
                                    25 * fem, 17 * fem, 25 * fem, 15.33 * fem),
                                width: double.infinity,
                                height: 83.33 * fem,
                                child: Row(
                                  crossAxisAlignment: CrossAxisAlignment.center,
                                  children: [
                                    Container(
                                      // coin50pxlightneojrM (I756:1848;133:95)
                                      margin: EdgeInsets.fromLTRB(
                                          0 * fem, 0 * fem, 19 * fem, 1 * fem),
                                      width: 50 * fem,
                                      height: 50 * fem,
                                      child: Image.asset(
                                        'lib/assets/neo.png',
                                        width: 50 * fem,
                                        height: 50 * fem,
                                      ),
                                    ),
                                    Container(
                                      // autogroupmkt33c9 (WBCPkgvbUv4jjbor5YmKT3)
                                      margin: EdgeInsets.fromLTRB(
                                          0 * fem, 0 * fem, 97 * fem, 0 * fem),
                                      height: double.infinity,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: [
                                          Container(
                                            // neoyVo (I756:1848;133:94)
                                            margin: EdgeInsets.fromLTRB(0 * fem,
                                                0 * fem, 0 * fem, 6 * fem),
                                            child: Text(
                                              'Art Masterclass',
                                              style: TextStyle(
                                                fontFamily: 'Poppins',
                                                fontSize: 18 * ffem,
                                                fontWeight: FontWeight.w500,
                                                height:
                                                    1.3333333333 * ffem / fem,
                                                letterSpacing:
                                                    0.0180000003 * fem,
                                                color: Color(0xff0f172a),
                                              ),
                                            ),
                                          ),
                                          Text(
                                            // neogQD (I756:1848;133:91)
                                            'NEO',
                                            style: TextStyle(
                                              fontFamily: 'Poppins',
                                              fontSize: 14 * ffem,
                                              fontWeight: FontWeight.w500,
                                              height: 1.5 * ffem / fem,
                                              color: Color(0xff9294a3),
                                            ),
                                          ),
                                        ],
                                      ),
                                    ),
                                    Container(
                                      // autogroupz49jRMo (WBCPrgkbuxMtTKsLYiz49j)
                                      width: 78 * fem,
                                      height: double.infinity,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.end,
                                        children: [
                                          Container(
                                            // YxD (I756:1848;133:96)
                                            margin: EdgeInsets.fromLTRB(0 * fem,
                                                0 * fem, 0 * fem, 3 * fem),
                                            child: Text(
                                              '\$24',
                                              textAlign: TextAlign.right,
                                              style: TextStyle(
                                                fontFamily: 'Poppins',
                                                fontSize: 18 * ffem,
                                                fontWeight: FontWeight.w500,
                                                height: 1.5 * ffem / fem,
                                                color: Color(0xff26273c),
                                              ),
                                            ),
                                          ),
                                          Container(
                                            // autogroup5zzbGNR (WBCPxWvDn5RfzE3BAp5zZB)
                                            width: double.infinity,
                                            height: 21 * fem,
                                            child: Center(
                                              child: Text(
                                                '2 days ago',
                                                textAlign: TextAlign.right,
                                                style: TextStyle(
                                                  fontFamily: 'Poppins',
                                                  fontSize: 14 * ffem,
                                                  fontWeight: FontWeight.w500,
                                                  height: 1.5 * ffem / fem,
                                                  color: Color(0xff9294a3),
                                                ),
                                              ),
                                            ),
                                          ),
                                        ],
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                              Container(
                                // listwithoutgraphlightbitcoinmp (756:1849)
                                margin: EdgeInsets.fromLTRB(
                                    0 * fem, 0 * fem, 0 * fem, 10 * fem),
                                padding: EdgeInsets.fromLTRB(
                                    25 * fem, 17 * fem, 25 * fem, 15.33 * fem),
                                width: double.infinity,
                                height: 83.33 * fem,
                                child: Row(
                                  crossAxisAlignment: CrossAxisAlignment.center,
                                  children: [
                                    Container(
                                      // coin50pxlightbitcoinrbX (I756:1849;133:5)
                                      margin: EdgeInsets.fromLTRB(
                                          0 * fem, 0 * fem, 19 * fem, 1 * fem),
                                      width: 50 * fem,
                                      height: 50 * fem,
                                      child: Image.asset(
                                        'lib/assets/bitcoin.png',
                                        width: 50 * fem,
                                        height: 50 * fem,
                                      ),
                                    ),
                                    Container(
                                      // autogroupnxpxa1j (WBCQLqcMq4JczsNcadnxPX)
                                      margin: EdgeInsets.fromLTRB(
                                          0 * fem, 0 * fem, 97 * fem, 0 * fem),
                                      height: double.infinity,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: [
                                          Container(
                                            // bitcoinuZo (I756:1849;42:100)
                                            margin: EdgeInsets.fromLTRB(0 * fem,
                                                0 * fem, 0 * fem, 6 * fem),
                                            child: Text(
                                              'Graffitti Mastery',
                                              style: TextStyle(
                                                fontFamily: 'Poppins',
                                                fontSize: 18 * ffem,
                                                fontWeight: FontWeight.w500,
                                                height:
                                                    1.3333333333 * ffem / fem,
                                                letterSpacing:
                                                    0.0180000003 * fem,
                                                color: Color(0xff0f172a),
                                              ),
                                            ),
                                          ),
                                          Text(
                                            // btc1cq (I756:1849;42:101)
                                            'Maria',
                                            style: TextStyle(
                                              fontFamily: 'Poppins',
                                              fontSize: 14 * ffem,
                                              fontWeight: FontWeight.w500,
                                              height: 1.5 * ffem / fem,
                                              color: Color(0xff9294a3),
                                            ),
                                          ),
                                        ],
                                      ),
                                    ),
                                    Container(
                                      // autogroupgty5kqK (WBCQT5vx7USpVqGapSgtY5)
                                      height: double.infinity,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.end,
                                        children: [
                                          Container(
                                            // Vnu (I756:1849;42:104)
                                            margin: EdgeInsets.fromLTRB(0 * fem,
                                                0 * fem, 0 * fem, 3 * fem),
                                            child: Text(
                                              '\$99',
                                              textAlign: TextAlign.right,
                                              style: TextStyle(
                                                fontFamily: 'Poppins',
                                                fontSize: 18 * ffem,
                                                fontWeight: FontWeight.w500,
                                                height: 1.5 * ffem / fem,
                                                color: Color(0xff26273c),
                                              ),
                                            ),
                                          ),
                                          Text(
                                            // oob (I756:1849;42:105)
                                            '2 hours ago',
                                            textAlign: TextAlign.right,
                                            style: TextStyle(
                                              fontFamily: 'Poppins',
                                              fontSize: 14 * ffem,
                                              fontWeight: FontWeight.w500,
                                              height: 1.5 * ffem / fem,
                                              color: Color(0xff9294a3),
                                            ),
                                          ),
                                        ],
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                              Container(
                                margin: EdgeInsets.fromLTRB(
                                    0 * fem, 0 * fem, 0 * fem, 10 * fem),
                                // listwithoutgraphlightachainTdF (756:1850)
                                padding: EdgeInsets.fromLTRB(
                                    25 * fem, 17 * fem, 25 * fem, 15.33 * fem),
                                width: double.infinity,
                                height: 83.33 * fem,
                                child: Row(
                                  crossAxisAlignment: CrossAxisAlignment.center,
                                  children: [
                                    Container(
                                      // coin50pxlightachainxa1 (I756:1850;133:49)
                                      margin: EdgeInsets.fromLTRB(
                                          0 * fem, 0 * fem, 19 * fem, 1 * fem),
                                      width: 50 * fem,
                                      height: 50 * fem,
                                      child: Image.asset(
                                        'lib/assets/achain.png',
                                        width: 50 * fem,
                                        height: 50 * fem,
                                      ),
                                    ),
                                    Container(
                                      // autogroupjeqmG4u (WBCQqzST9fdYfNDoh5JeQm)
                                      margin: EdgeInsets.fromLTRB(
                                          0 * fem, 0 * fem, 97 * fem, 0 * fem),
                                      height: double.infinity,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: [
                                          Container(
                                            // achainCDT (I756:1850;133:48)
                                            margin: EdgeInsets.fromLTRB(0 * fem,
                                                0 * fem, 0 * fem, 6 * fem),
                                            child: Text(
                                              'Figma Mastery',
                                              style: TextStyle(
                                                fontFamily: 'Poppins',
                                                fontSize: 18 * ffem,
                                                fontWeight: FontWeight.w500,
                                                height:
                                                    1.3333333333 * ffem / fem,
                                                letterSpacing:
                                                    0.0180000003 * fem,
                                                color: Color(0xff0f172a),
                                              ),
                                            ),
                                          ),
                                          Text(
                                            // act6pd (I756:1850;133:45)
                                            'ACT',
                                            style: TextStyle(
                                              fontFamily: 'Poppins',
                                              fontSize: 14 * ffem,
                                              fontWeight: FontWeight.w500,
                                              height: 1.5 * ffem / fem,
                                              color: Color(0xff9294a3),
                                            ),
                                          ),
                                        ],
                                      ),
                                    ),
                                    Container(
                                      // autogroup7nbfFBj (WBCQxpaQRJ5XKDjZPh7Nbf)
                                      height: double.infinity,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.end,
                                        children: [
                                          Container(
                                            // zv1 (I756:1850;133:50)
                                            margin: EdgeInsets.fromLTRB(0 * fem,
                                                0 * fem, 0 * fem, 3 * fem),
                                            child: Text(
                                              '\$19',
                                              textAlign: TextAlign.right,
                                              style: TextStyle(
                                                fontFamily: 'Poppins',
                                                fontSize: 18 * ffem,
                                                fontWeight: FontWeight.w500,
                                                height: 1.5 * ffem / fem,
                                                color: Color(0xff26273c),
                                              ),
                                            ),
                                          ),
                                          Text(
                                            // un5 (I756:1850;133:46)
                                            '3 months ago',
                                            textAlign: TextAlign.right,
                                            style: TextStyle(
                                              fontFamily: 'Poppins',
                                              fontSize: 14 * ffem,
                                              fontWeight: FontWeight.w500,
                                              height: 1.5 * ffem / fem,
                                              color: Color(0xff9294a3),
                                            ),
                                          ),
                                        ],
                                      ),
                                    ),
                                  ],
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
            ),
          ],
        ),
      ),
    );
  }
}
