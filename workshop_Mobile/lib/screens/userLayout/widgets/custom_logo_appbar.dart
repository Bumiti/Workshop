// custom_appbar.dart

import 'package:flutter/material.dart';

class CustomLogoAppBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return RichText(
      text: TextSpan(
        style: DefaultTextStyle.of(context).style,
        children: <TextSpan>[
          TextSpan(
            text: 'Infinity ',
            style: TextStyle(
              color: Colors.blue,
              fontSize: 20,
              decoration: TextDecoration.none,
            ),
          ),
          TextSpan(
            text: 'Connect',
            style: TextStyle(
              color: Colors.black,
              fontSize: 18,
              decoration: TextDecoration.none,
            ),
          ),
        ],
      ),
    );
  }
}
