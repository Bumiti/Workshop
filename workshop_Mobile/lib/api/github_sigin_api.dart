import 'dart:async';
import 'package:http/http.dart' as http;
import 'dart:convert';

import 'package:url_launcher/url_launcher.dart';

final authorizationEndpoint =
    Uri.parse('https://github.com/login/oauth/authorize');
final tokenEndpoint = Uri.parse('https://github.com/login/oauth/access_token');
const clientId = '13834b566cdff854a8a4';
const clientSecret = '61378b750d943c09f83985793ea5a4c5d2428af9';
final redirectUrl = Uri.parse('myapp://oauth-callback');

class GitHubSignInApi {
  static Future<void> handleLogin() async {
    final authorizationUrl =
        '$authorizationEndpoint?client_id=$clientId&redirect_uri=$redirectUrl&scope=read:user repo';
    print(authorizationUrl);
    await openWebPage(authorizationUrl);

    final responseUrl = await listenForRedirect();
    final Map<String, String> queryParams = Uri.parse(responseUrl).queryParameters;
    final String code = queryParams['code'] ?? '';

    // Requesting the access token using the authorization code
    final tokenResponse = await http.post(
      tokenEndpoint,
      headers: {'Accept': 'application/json'},
      body: {
        'client_id': clientId,
        'client_secret': clientSecret,
        'code': code,
        'redirect_uri': redirectUrl.toString(),

      },
    );

    if (tokenResponse.statusCode == 200) {
      // Successful authentication, you can now use the access token
      final Map<String, dynamic> tokenData = jsonDecode(tokenResponse.body);
      final String accessToken = tokenData['access_token'];
      print('Access Token: $accessToken');
    } else {
      print('Authentication failed: ${tokenResponse.body}');
    }
  }
}

Future<void> openWebPage(String url) async {
  final Uri _url = Uri.parse(url);
  if (!await launchUrl(_url)) {
    throw Exception('Could not launch $_url');
  }
}

Future<String> listenForRedirect() async {
  // Implement this function to listen for the redirect URL after the user logs in.
  // You might need to use plugins or other methods to handle this depending on your app's structure.

  // Assume you have a StreamController<String> to handle the redirect URL
  final StreamController<String> redirectController = StreamController<String>();

  // Add a listener to the stream
  final StreamSubscription<String> subscription = redirectController.stream.listen((url) {
    // Handle the redirect URL here
    // You can also close the stream if needed
    redirectController.close();
  });

  // Return the future which completes when the redirect URL is received
  return redirectController.stream.first;
}

