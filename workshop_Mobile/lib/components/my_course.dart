import 'package:flutter/material.dart';

class MyCourse extends StatelessWidget {
  const MyCourse({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
        width: 315,
        height: 155,
        decoration: ShapeDecoration(
          gradient: const LinearGradient(
            begin: Alignment(0.98, -0.18),
            end: Alignment(
              -0.98,
              0.18,
            ),
            colors: [Color(0xFFA3E3FF), Color(0xFF6CABE3), Color(0xFF0072F0)],
          ),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(16),
          ),
        ),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 10),
          child: Column(
            children: [
              const SizedBox(
                height: 20,
              ),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'Acoustic Guitar',
                    style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 20,
                        color: Colors.white),
                  ),
                  const SizedBox(
                    height: 15,
                  ),
                  const Text(
                    'The course will makes master guitar in less than 3 weeks.',
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 9.5,
                        fontWeight: FontWeight.w300),
                  ),
                  const Text(
                    'Keep your energy and your stamina hyped up in every note you played...',
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 9.5,
                        fontWeight: FontWeight.w300),
                  ),
                  const SizedBox(
                    height: 30,
                  ),
                  Row(
                    children: [
                      const Text(
                        '10/10/2023',
                        style: TextStyle(
                            color: Colors.white,
                            fontWeight: FontWeight.bold,
                            fontSize: 11),
                      ),
                      const Spacer(),
                      Container(
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(16),
                            color: Colors.white),
                        alignment: Alignment.centerLeft,
                        height: 28,
                        width: 73,
                        child: TextButton(
                            onPressed: () {
                              print('Yoyo');
                            },
                            child: const Row(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: [
                                Text(
                                  'Explore',
                                  style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      color: Colors.black,
                                      fontSize: 10),
                                ),
                              ],
                            )),
                      ),
                    ],
                  ),
                ],
              ),
            ],
          ),
        ));
  }
}
