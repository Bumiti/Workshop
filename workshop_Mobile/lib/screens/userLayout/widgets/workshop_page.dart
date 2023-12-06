// ignore_for_file: avoid_unnecessary_containers

import 'package:flutter/material.dart';
import 'package:workshop_mobi/model/student/workshop_endroll.dart';

class WorkshopManagerStudent extends StatelessWidget {
  final workshopEndrollResponses workshopEndroll;
  WorkshopManagerStudent({Key? key, required this.workshopEndroll});

  @override
  Widget build(BuildContext context) {
    final Size screenSize = MediaQuery.of(context).size;
    double widthRatio = screenSize.width / 430;
    double heightRatio = screenSize.height / 932;
    print(workshopEndroll);
    return SingleChildScrollView(
      child: Column(
        children: [
          Container(
            width: 430 * widthRatio,
            height: 932 * heightRatio,
            clipBehavior: Clip.antiAlias,
            decoration: const BoxDecoration(color: Colors.white),
            child: Stack(
              children: [
                // Màu nền phông xanh
                BackgroundCard(
                    widthRatio: widthRatio, heightRatio: heightRatio),
                // Hiển thị
                CardQrContent(
                    widthRatio: widthRatio, workshopEndroll: workshopEndroll),
                // Tên đầu
                HeaderWorsshopCard(
                    widthRatio: widthRatio,
                    heightRatio: heightRatio,
                    workshopEndroll: workshopEndroll),
                //nút call back
                BackButton(widthRatio: widthRatio, heightRatio: heightRatio),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class CardQrContent extends StatelessWidget {
  const CardQrContent({
    super.key,
    required this.widthRatio,
    required this.workshopEndroll,
  });

  final double widthRatio;
  final workshopEndrollResponses workshopEndroll;

  @override
  Widget build(BuildContext context) {
    return Positioned(
      left: 17 * widthRatio,
      top: 210 * widthRatio,
      child: SizedBox(
        width: 395 * widthRatio,
        height: 560 * widthRatio,
        child: Stack(
          children: [
            Positioned(
              left: 0,
              top: 0,
              child: Container(
                width: 395 * widthRatio,
                height: 560 * widthRatio,
                decoration: ShapeDecoration(
                  color: Colors.white,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(31),
                  ),
                  shadows: [
                    BoxShadow(
                      color: const Color(0x3F000000),
                      blurRadius: 4 * widthRatio,
                      offset: const Offset(0, 4),
                      spreadRadius: 0,
                    )
                  ],
                ),
              ),
            ),
            Positioned(
              left: 111 * widthRatio,
              top: 12 * widthRatio,
              child: Text(
                'Your QR Code',
                textAlign: TextAlign.center,
                style: TextStyle(
                  color: Colors.black,
                  fontSize: 25 * widthRatio,
                  fontFamily: 'Poppins',
                  fontWeight: FontWeight.w700,
                  height: 0,
                   decoration: TextDecoration.none,
                ),
              ),
            ),
            Positioned(
              left: 36 * widthRatio,
              top: 25 * widthRatio,
              child: SizedBox(
                width: 324 * widthRatio,
                height: 268 * widthRatio,
                child: Text.rich(
                  TextSpan(
                    children: [
                      const TextSpan(
                        text: '\n',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Actor',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      const TextSpan(
                        text: 'Short description:',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Baloo',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      const TextSpan(
                        text: ' ',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Actor',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      TextSpan(
                        text: '${workshopEndroll.name}\n',
                        style: const TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Baloo 2',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      const TextSpan(
                        text: 'Location:',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Baloo',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      TextSpan(
                        text: ' ${workshopEndroll.courseLocations[0].name}\n',
                        style: const TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Baloo 2',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      const TextSpan(
                        text: 'Address:',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Baloo',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      TextSpan(
                        text:
                            ' ${workshopEndroll.courseLocations[0].address}\n',
                        style: const TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Baloo 2',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      const TextSpan(
                        text: 'Guess:',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Baloo',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      const TextSpan(
                        text: ' ',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Actor',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      TextSpan(
                        text: '${workshopEndroll.teacher}\n',
                        style: const TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Baloo 2',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      const TextSpan(
                        text: 'Time:',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Baloo',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      const TextSpan(
                        text: ' ',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Actor',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                      const TextSpan(
                        text: '17:00',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 20,
                          fontFamily: 'Baloo 2',
                          fontWeight: FontWeight.w400,
                          height: 0,
                           decoration: TextDecoration.none,
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            Positioned(
              left: 80 * widthRatio,
              top: 220 * widthRatio,
              child: Image.network(
                '${workshopEndroll.urlQrCode}',
                width: 250 * widthRatio,
                height: 250 * widthRatio,
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class BackgroundCard extends StatelessWidget {
  const BackgroundCard({
    super.key,
    required this.widthRatio,
    required this.heightRatio,
  });

  final double widthRatio;
  final double heightRatio;

  @override
  Widget build(BuildContext context) {
    return Positioned(
      left: 0,
      top: 0,
      child: Container(
        width: 430 * widthRatio,
        height: 932 * heightRatio,
        padding: const EdgeInsets.symmetric(vertical: 9),
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment(0.00, -1.00),
            end: Alignment(0, 1),
            colors: [
              Color(0xFF27AAE1),
              Color(0xCF9FC9ED),
              Color(0xBEA6CDEF),
              Color(0xA4B2D4F1),
              Color(0x7AC6DFF4),
              Color(0x3A9E9E9E)
            ],
          ),
        ),
      ),
    );
  }
}

class HeaderWorsshopCard extends StatelessWidget {
  const HeaderWorsshopCard({
    super.key,
    required this.widthRatio,
    required this.heightRatio,
    required this.workshopEndroll,
  });

  final double widthRatio;
  final double heightRatio;
  final workshopEndrollResponses workshopEndroll;

  @override
  Widget build(BuildContext context) {
    return Positioned(
      left: 102,
      top: 92,
      child: Container(
        width: 225 * widthRatio,
        height: 200 * heightRatio,
        clipBehavior: Clip.antiAlias,
        decoration: const BoxDecoration(),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Container(
              clipBehavior: Clip.antiAlias,
              decoration: const BoxDecoration(),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Container(
                    clipBehavior: Clip.antiAlias,
                    decoration: const BoxDecoration(),
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Text(
                          '${workshopEndroll.name}',
                          textAlign: TextAlign.center,
                          style: const TextStyle(
                            color: Colors.white,
                            fontSize: 26,
                            fontFamily: 'Poppins',
                            fontWeight: FontWeight.w500,
                            height: 0,
                             decoration: TextDecoration.none,
                          ),
                        ),
                        const SizedBox(height: 4),
                        Text(
                          'The Opening',
                          textAlign: TextAlign.center,
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 16 * widthRatio,
                            fontFamily: 'Poppins',
                            fontWeight: FontWeight.w500,
                            height: 0,
                             decoration: TextDecoration.none,
                          ),
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 16),
                  Container(
                    width: 200 * widthRatio,
                    decoration: ShapeDecoration(
                      shape: RoundedRectangleBorder(
                        side: BorderSide(
                          width: 1,
                          strokeAlign: BorderSide.strokeAlignCenter,
                          color: Colors.white.withOpacity(0.3499999940395355),
                        ),
                      ),
                    ),
                  ),
                  const SizedBox(height: 16),
                  Container(
                    clipBehavior: Clip.antiAlias,
                    decoration: const BoxDecoration(),
                    child: Row(
                      mainAxisSize: MainAxisSize.min,
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Text(
                          '10',
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 14 * widthRatio,
                            fontFamily: 'Poppins',
                            fontWeight: FontWeight.w500,
                            height: 0,
                          ),
                        ),
                        const SizedBox(width: 5),
                        Container(
                          width: 4 * widthRatio,
                          height: 4 * heightRatio,
                          decoration: ShapeDecoration(
                            color: Colors.white.withOpacity(0.3499999940395355),
                            shape: const OvalBorder(),
                          ),
                        ),
                        const SizedBox(width: 5),
                        const Text(
                          '10 -2023',
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 14,
                            fontFamily: 'Poppins',
                            fontWeight: FontWeight.w500,
                            height: 0,
                             decoration: TextDecoration.none,
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
    );
  }
}

class BackButton extends StatelessWidget {
  const BackButton({
    super.key,
    required this.widthRatio,
    required this.heightRatio,
  });

  final double widthRatio;
  final double heightRatio;

  @override
  Widget build(BuildContext context) {
    return Positioned(
      left: 13,
      top: 24,
      child: SizedBox(
        width: 40,
        height: 40,
        child: Stack(
          alignment: Alignment.center,
          children: [
            Container(
              width: 40 * widthRatio,
              height: 40 * widthRatio,
              decoration: const ShapeDecoration(
                color: Color.fromARGB(255, 161, 52, 52),
                shape: CircleBorder(),
              ),
            ),
            Container(
              width: 32.52 * widthRatio,
              height: 32.52 * heightRatio,
              clipBehavior: Clip.antiAlias,
              decoration: ShapeDecoration(
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(40),
                ),
              ),
            ),
            Container(
              child: IconButton(
                icon: const Icon(Icons.arrow_back, color: Colors.white),
                onPressed: () {
                  Navigator.pop(context);
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
