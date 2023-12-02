// import 'package:flutter/material.dart';



// class StudentWalletPage extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     return Column(
//       children: [
//         Container(
//           width: 422,
//           height: 751,
//           clipBehavior: Clip.antiAlias,
//           decoration: BoxDecoration(
//             gradient: LinearGradient(
//               begin: Alignment(-0.22, -0.98),
//               end: Alignment(0.22, 0.98),
//               colors: [Color(0xFF4BDEFF), Color(0xFF2CD8E3), Color(0xFF50D3E4), Color(0xFF7CD8FF), Color(0xFF5CE1FF), Color(0xFF54CCFF), Color(0xFF84CEEE), Color(0xFF90D3EF), Color(0xFFCAEAF7), Color(0xFFE1F3FA), Colors.white],
//             ),
//             boxShadow: [
//               BoxShadow(
//                 color: Color(0x14201B39),
//                 blurRadius: 40,
//                 offset: Offset(0, 4),
//                 spreadRadius: 0,
//               )
//             ],
//           ),
//           child: Stack(
//             children: [
//               Positioned(
//                 left: 240,
//                 top: 74,
//                 child: Container(
//                   width: 244,
//                   height: 81,
//                   padding: const EdgeInsets.only(top: 10, right: 10, bottom: 10),
//                   child: Row(
//                     mainAxisSize: MainAxisSize.min,
//                     mainAxisAlignment: MainAxisAlignment.start,
//                     crossAxisAlignment: CrossAxisAlignment.start,
//                     children: [
//                       Container(
//                         width: 242,
//                         padding: const EdgeInsets.all(10),
//                         decoration: BoxDecoration(
//                           image: DecorationImage(
//                             image: NetworkImage("https://via.placeholder.com/242x242"),
//                             fit: BoxFit.fill,
//                           ),
//                         ),
//                       ),
//                     ],
//                   ),
//                 ),
//               ),
//               Positioned(
//                 left: 0,
//                 top: 366,
//                 child: Container(
//                   width: 422,
//                   height: 290,
//                   padding: const EdgeInsets.all(10),
//                   child: Column(
//                     mainAxisSize: MainAxisSize.min,
//                     mainAxisAlignment: MainAxisAlignment.center,
//                     crossAxisAlignment: CrossAxisAlignment.center,
//                     children: [
//                       Container(
//                         width: 435,
//                         height: 83.33,
//                         child: Stack(
//                           children: [
//                             Positioned(
//                               left: 0,
//                               top: 0,
//                               child: Container(width: 435, height: 83.33),
//                             ),
//                             Positioned(
//                               left: 94,
//                               top: 47,
//                               child: Text(
//                                 'NEO',
//                                 style: TextStyle(
//                                   color: Color(0xFF9294A3),
//                                   fontSize: 14,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0,
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 332,
//                               top: 47,
//                               child: Text(
//                                 '2 days ago',
//                                 textAlign: TextAlign.right,
//                                 style: TextStyle(
//                                   color: Color(0xFF9294A3),
//                                   fontSize: 14,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0,
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 94,
//                               top: 17,
//                               child: Text(
//                                 'Art Masterclass',
//                                 style: TextStyle(
//                                   color: Color(0xFF0F172A),
//                                   fontSize: 18,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0.07,
//                                   letterSpacing: 0.02,
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 25,
//                               top: 17,
//                               child: Container(
//                                 width: 50,
//                                 height: 50,
//                                 child: Stack(
//                                   children: [
//                                     Positioned(
//                                       left: 0,
//                                       top: 0,
//                                       child: Container(
//                                         width: 50,
//                                         height: 50,
//                                         decoration: ShapeDecoration(
//                                           color: Color(0xFFD4EEC9),
//                                           shape: OvalBorder(),
//                                         ),
//                                       ),
//                                     ),
//                                     Positioned(
//                                       left: 13,
//                                       top: 13,
//                                       child: Container(
//                                         width: 24,
//                                         height: 24,
//                                         clipBehavior: Clip.antiAlias,
//                                         decoration: BoxDecoration(),
//                                         child: Stack(
//                                           children: [
//                                             Positioned(
//                                               left: 2.80,
//                                               top: 1.45,
//                                               child: Container(
//                                                 width: 20.39,
//                                                 height: 22.09,
//                                                 decoration: BoxDecoration(
//                                                   image: DecorationImage(
//                                                     image: NetworkImage("https://via.placeholder.com/20x22"),
//                                                     fit: BoxFit.fill,
//                                                   ),
//                                                 ),
//                                               ),
//                                             ),
//                                           ],
//                                         ),
//                                       ),
//                                     ),
//                                   ],
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 376,
//                               top: 17,
//                               child: Text(
//                                 '\$24',
//                                 textAlign: TextAlign.right,
//                                 style: TextStyle(
//                                   color: Color(0xFF26273C),
//                                   fontSize: 18,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0,
//                                 ),
//                               ),
//                             ),
//                           ],
//                         ),
//                       ),
//                       const SizedBox(height: 10),
//                       Container(
//                         width: 435,
//                         height: 83.33,
//                         child: Stack(
//                           children: [
//                             Positioned(
//                               left: 0,
//                               top: 0,
//                               child: Container(width: 435, height: 83.33),
//                             ),
//                             Positioned(
//                               left: 94,
//                               top: 47,
//                               child: Text(
//                                 'Maria',
//                                 style: TextStyle(
//                                   color: Color(0xFF9294A3),
//                                   fontSize: 14,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0,
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 326,
//                               top: 47,
//                               child: Text(
//                                 '2 hours ago',
//                                 textAlign: TextAlign.right,
//                                 style: TextStyle(
//                                   color: Color(0xFF9294A3),
//                                   fontSize: 14,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0,
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 361,
//                               top: 51,
//                               child: Container(
//                                 width: 12,
//                                 height: 12,
//                                 clipBehavior: Clip.antiAlias,
//                                 decoration: BoxDecoration(),
//                               ),
//                             ),
//                             Positioned(
//                               left: 94,
//                               top: 17,
//                               child: Text(
//                                 'Graffitti Mastery',
//                                 style: TextStyle(
//                                   color: Color(0xFF0F172A),
//                                   fontSize: 18,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0,
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 25,
//                               top: 17,
//                               child: Container(
//                                 width: 50,
//                                 height: 50,
//                                 child: Stack(
//                                   children: [
//                                     Positioned(
//                                       left: 0,
//                                       top: 0,
//                                       child: Container(
//                                         width: 50,
//                                         height: 50,
//                                         decoration: ShapeDecoration(
//                                           color: Color(0xFFFFE4C3),
//                                           shape: OvalBorder(),
//                                         ),
//                                       ),
//                                     ),
//                                     Positioned(
//                                       left: 13,
//                                       top: 13,
//                                       child: Container(
//                                         width: 24,
//                                         height: 24,
//                                         clipBehavior: Clip.antiAlias,
//                                         decoration: BoxDecoration(),
//                                         child: Stack(children: [
                                        
//                                         ]),
//                                       ),
//                                     ),
//                                   ],
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 375,
//                               top: 17,
//                               child: Text(
//                                 '\$99',
//                                 textAlign: TextAlign.right,
//                                 style: TextStyle(
//                                   color: Color(0xFF26273C),
//                                   fontSize: 18,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0,
//                                 ),
//                               ),
//                             ),
//                           ],
//                         ),
//                       ),
//                       const SizedBox(height: 10),
//                       Container(
//                         width: 435,
//                         height: 83.33,
//                         child: Stack(
//                           children: [
//                             Positioned(
//                               left: 0,
//                               top: 0,
//                               child: Container(width: 435, height: 83.33),
//                             ),
//                             Positioned(
//                               left: 94,
//                               top: 47,
//                               child: Text(
//                                 'ACT',
//                                 style: TextStyle(
//                                   color: Color(0xFF9294A3),
//                                   fontSize: 14,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0,
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 312,
//                               top: 47,
//                               child: Text(
//                                 '3 months ago',
//                                 textAlign: TextAlign.right,
//                                 style: TextStyle(
//                                   color: Color(0xFF9294A3),
//                                   fontSize: 14,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0,
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 361,
//                               top: 51,
//                               child: Container(
//                                 width: 12,
//                                 height: 12,
//                                 clipBehavior: Clip.antiAlias,
//                                 decoration: BoxDecoration(),
//                               ),
//                             ),
//                             Positioned(
//                               left: 94,
//                               top: 17,
//                               child: Text(
//                                 'Figma Mastery',
//                                 style: TextStyle(
//                                   color: Color(0xFF0F172A),
//                                   fontSize: 18,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0.07,
//                                   letterSpacing: 0.02,
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 25,
//                               top: 17,
//                               child: Container(
//                                 width: 50,
//                                 height: 50,
//                                 child: Stack(
//                                   children: [
//                                     Positioned(
//                                       left: 0,
//                                       top: 0,
//                                       child: Container(
//                                         width: 50,
//                                         height: 50,
//                                         decoration: ShapeDecoration(
//                                           color: Color(0xFFE0E2FF),
//                                           shape: OvalBorder(),
//                                         ),
//                                       ),
//                                     ),
//                                     Positioned(
//                                       left: 13,
//                                       top: 13,
//                                       child: Container(
//                                         width: 24,
//                                         height: 24,
//                                         clipBehavior: Clip.antiAlias,
//                                         decoration: BoxDecoration(),
//                                         child: Stack(
//                                           children: [
//                                             Positioned(
//                                               left: 1.71,
//                                               top: 3,
//                                               child: Container(
//                                                 width: 20.57,
//                                                 height: 18,
//                                                 decoration: BoxDecoration(
//                                                   image: DecorationImage(
//                                                     image: NetworkImage("https://via.placeholder.com/21x18"),
//                                                     fit: BoxFit.fill,
//                                                   ),
//                                                 ),
//                                               ),
//                                             ),
//                                           ],
//                                         ),
//                                       ),
//                                     ),
//                                   ],
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 380,
//                               top: 17,
//                               child: Text(
//                                 '\$19',
//                                 textAlign: TextAlign.right,
//                                 style: TextStyle(
//                                   color: Color(0xFF26273C),
//                                   fontSize: 18,
//                                   fontFamily: 'Poppins',
//                                   fontWeight: FontWeight.w500,
//                                   height: 0,
//                                 ),
//                               ),
//                             ),
//                           ],
//                         ),
//                       ),
//                     ],
//                   ),
//                 ),
//               ),
//               Positioned(
//                 left: 122,
//                 top: 296,
//                 child: SizedBox(
//                   width: 166,
//                   child: Text(
//                     'Last Transaction',
//                     style: TextStyle(
//                       color: Color(0xFF191C32),
//                       fontSize: 20,
//                       fontFamily: 'Poppins',
//                       fontWeight: FontWeight.w500,
//                       height: 0,
//                     ),
//                   ),
//                 ),
//               ),
//               Positioned(
//                 left: 26,
//                 top: 105,
//                 child: Container(
//                   width: 371,
//                   child: Row(
//                     mainAxisSize: MainAxisSize.min,
//                     mainAxisAlignment: MainAxisAlignment.start,
//                     crossAxisAlignment: CrossAxisAlignment.center,
//                     children: [
//                       Expanded(
//                         child: Container(
//                           height: 30,
//                           clipBehavior: Clip.antiAlias,
//                           decoration: BoxDecoration(),
//                           child: Row(
//                             mainAxisSize: MainAxisSize.min,
//                             mainAxisAlignment: MainAxisAlignment.start,
//                             crossAxisAlignment: CrossAxisAlignment.center,
//                             children: [
//                               Container(
//                                 height: 30,
//                                 padding: const EdgeInsets.only(bottom: 4),
//                                 child: Row(
//                                   mainAxisSize: MainAxisSize.min,
//                                   mainAxisAlignment: MainAxisAlignment.center,
//                                   crossAxisAlignment: CrossAxisAlignment.center,
//                                   children: [
//                                     SizedBox(
//                                       width: 371,
//                                       child: Text(
//                                         '\$890,00',
//                                         style: TextStyle(
//                                           color: Color(0xFF0F172A),
//                                           fontSize: 32,
//                                           fontFamily: 'Readex Pro',
//                                           fontWeight: FontWeight.w600,
//                                           height: 0.03,
//                                           letterSpacing: 0.03,
//                                         ),
//                                       ),
//                                     ),
//                                   ],
//                                 ),
//                               ),
//                             ],
//                           ),
//                         ),
//                       ),
//                     ],
//                   ),
//                 ),
//               ),
//               Positioned(
//                 left: 27,
//                 top: 64,
//                 child: Container(
//                   width: 371,
//                   child: Row(
//                     mainAxisSize: MainAxisSize.min,
//                     mainAxisAlignment: MainAxisAlignment.start,
//                     crossAxisAlignment: CrossAxisAlignment.center,
//                     children: [
//                       Container(
//                         height: 30,
//                         clipBehavior: Clip.antiAlias,
//                         decoration: BoxDecoration(),
//                         child: Row(
//                           mainAxisSize: MainAxisSize.min,
//                           mainAxisAlignment: MainAxisAlignment.start,
//                           crossAxisAlignment: CrossAxisAlignment.center,
//                           children: [
//                             Container(
//                               height: 30,
//                               padding: const EdgeInsets.only(bottom: 4),
//                               child: Row(
//                                 mainAxisSize: MainAxisSize.min,
//                                 mainAxisAlignment: MainAxisAlignment.center,
//                                 crossAxisAlignment: CrossAxisAlignment.center,
//                                 children: [
//                                   SizedBox(
//                                     width: 333.73,
//                                     child: Text(
//                                       'Balance',
//                                       style: TextStyle(
//                                         color: Color(0xFF0F172A),
//                                         fontSize: 20,
//                                         fontFamily: 'Readex Pro',
//                                         fontWeight: FontWeight.w500,
//                                         height: 0.07,
//                                         letterSpacing: 0.02,
//                                       ),
//                                     ),
//                                   ),
//                                 ],
//                               ),
//                             ),
//                           ],
//                         ),
//                       ),
//                       const SizedBox(width: 11),
//                       Container(
//                         width: 39.27,
//                         height: 40.52,
//                         decoration: ShapeDecoration(
//                           image: DecorationImage(
//                             image: NetworkImage("https://via.placeholder.com/39x41"),
//                             fit: BoxFit.fill,
//                           ),
//                           shape: OvalBorder(),
//                         ),
//                       ),
//                     ],
//                   ),
//                 ),
//               ),
//               Positioned(
//                 left: 29,
//                 top: 180,
//                 child: Container(
//                   padding: const EdgeInsets.symmetric(horizontal: 23, vertical: 7),
//                   decoration: ShapeDecoration(
//                     color: Color(0xFFFAFBFB),
//                     shape: RoundedRectangleBorder(
//                       borderRadius: BorderRadius.circular(40),
//                     ),
//                   ),
//                   child: Row(
//                     mainAxisSize: MainAxisSize.min,
//                     mainAxisAlignment: MainAxisAlignment.center,
//                     crossAxisAlignment: CrossAxisAlignment.center,
//                     children: [
//                       Text(
//                         'Deposit Now',
//                         textAlign: TextAlign.center,
//                         style: TextStyle(
//                           color: Color(0xFF26273C),
//                           fontSize: 14,
//                           fontFamily: 'Poppins',
//                           fontWeight: FontWeight.w600,
//                           height: 0,
//                         ),
//                       ),
//                     ],
//                   ),
//                 ),
//               ),
//               Positioned(
//                 left: -11,
//                 top: 668,
//                 child: Container(
//                   width: 453,
//                   height: 108,
//                   padding: const EdgeInsets.all(10),
//                   child: Column(
//                     mainAxisSize: MainAxisSize.min,
//                     mainAxisAlignment: MainAxisAlignment.center,
//                     crossAxisAlignment: CrossAxisAlignment.center,
//                     children: [
//                       Container(
//                         width: 430,
//                         height: 88,
//                         child: Stack(
//                           children: [
//                             Positioned(
//                               left: 0,
//                               top: 0,
//                               child: Container(
//                                 width: 430,
//                                 height: 88,
//                                 decoration: BoxDecoration(color: Colors.white),
//                               ),
//                             ),
//                             Positioned(
//                               left: 16,
//                               top: 18,
//                               child: Container(
//                                 height: 52,
//                                 child: Stack(
//                                   children: [
//                                     Positioned(
//                                       left: 0,
//                                       top: 35,
//                                       child: SizedBox(
//                                         width: 67,
//                                         height: 17,
//                                         child: Text(
//                                           'Home',
//                                           textAlign: TextAlign.center,
//                                           style: TextStyle(
//                                             color: Colors.black,
//                                             fontSize: 16,
//                                             fontFamily: 'SVN-Effra',
//                                             fontWeight: FontWeight.w400,
//                                             height: 0,
//                                           ),
//                                         ),
//                                       ),
//                                     ),
//                                   ],
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 109,
//                               top: 21,
//                               child: Container(
//                                 height: 49,
//                                 child: Stack(
//                                   children: [
//                                     Positioned(
//                                       left: 0,
//                                       top: 32,
//                                       child: SizedBox(
//                                         width: 95,
//                                         height: 17,
//                                         child: Text(
//                                           'Manage',
//                                           textAlign: TextAlign.center,
//                                           style: TextStyle(
//                                             color: Colors.black,
//                                             fontSize: 16,
//                                             fontFamily: 'SVN-Effra',
//                                             fontWeight: FontWeight.w400,
//                                             height: 0,
//                                           ),
//                                         ),
//                                       ),
//                                     ),
//                                   ],
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 219,
//                               top: 11,
//                               child: Container(
//                                 width: 95,
//                                 height: 59,
//                                 child: Stack(
//                                   children: [
//                                     Positioned(
//                                       left: 0,
//                                       top: 42,
//                                       child: SizedBox(
//                                         width: 95,
//                                         height: 17,
//                                         child: Text(
//                                           'Workshop',
//                                           textAlign: TextAlign.center,
//                                           style: TextStyle(
//                                             color: Colors.black,
//                                             fontSize: 16,
//                                             fontFamily: 'SVN-Effra',
//                                             fontWeight: FontWeight.w400,
//                                             height: 0,
//                                           ),
//                                         ),
//                                       ),
//                                     ),
//                                   ],
//                                 ),
//                               ),
//                             ),
//                             Positioned(
//                               left: 328,
//                               top: 24,
//                               child: Container(
//                                 height: 46,
//                                 child: Stack(
//                                   children: [
//                                     Positioned(
//                                       left: 0,
//                                       top: 29,
//                                       child: SizedBox(
//                                         width: 95,
//                                         height: 17,
//                                         child: Text(
//                                           'Wallet',
//                                           textAlign: TextAlign.center,
//                                           style: TextStyle(
//                                             color: Colors.black,
//                                             fontSize: 16,
//                                             fontFamily: 'SVN-Effra',
//                                             fontWeight: FontWeight.w400,
//                                             height: 0,
//                                           ),
//                                         ),
//                                       ),
//                                     ),
//                                   ],
//                                 ),
//                               ),
//                             ),
//                           ],
//                         ),
//                       ),
//                     ],
//                   ),
//                 ),
//               ),
//             ],
//           ),
//         ),
//       ],
//     );
//   }
// }