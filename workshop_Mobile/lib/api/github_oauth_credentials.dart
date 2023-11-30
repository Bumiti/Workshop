// import 'dart:io';

// import 'package:github/github.dart';
// import 'package:http/http.dart' as http;
// import 'package:oauth2/oauth2.dart' as oauth2;
// import 'package:url_launcher/url_launcher.dart';

// const githubClientId = '13834b566cdff854a8a4';
// const githubClientSecret = '61378b750d943c09f83985793ea5a4c5d2428af9';
// const githubScopes = ['repo', 'read:org'];
// final _authorizationEndpoint =
//     Uri.parse('https://github.com/login/oauth/authorize');
// final _tokenEndpoint = Uri.parse('https://github.com/login/oauth/access_token');

// class GitHubSignInApi {
//   static Future<oauth2.Client?> signIn() async {
//     HttpServer? _redirectServer;
//     try {
//       _redirectServer = await HttpServer.bind('localhost', 0);
//       var authenticatedHttpClient =
//           await _getOAuth2Client(Uri.parse('myapp://auth'));
 
//       return authenticatedHttpClient;
//     } catch (e) {
//       return null;
//     } finally {
//       await _redirectServer?.close();
//     }
//   }

//   static Future<oauth2.Client> _getOAuth2Client(Uri redirectUrl) async {
//     if (githubClientId.isEmpty || githubClientSecret.isEmpty) {
//       throw const GithubLoginException(
//           'githubClientId and githubClientSecret must not be empty.');
//     }
//     var grant = oauth2.AuthorizationCodeGrant(
//       githubClientId,
//       _authorizationEndpoint,
//       _tokenEndpoint,
//       secret: githubClientSecret,
//       httpClient: _JsonAcceptingHttpClient(),
//     );
//     var authorizationUrl =
//         grant.getAuthorizationUrl(redirectUrl, scopes: githubScopes);
// print(authorizationUrl);
//     await _redirect(authorizationUrl);
//     var responseQueryParameters = await _listen();
//     var client =
//         await grant.handleAuthorizationResponse(responseQueryParameters);
//     return client;
//   }

//   static Future<void> _redirect(Uri authorizationUrl) async {
//     if (await canLaunchUrl(authorizationUrl)) 
//     {
//       await launchUrl(authorizationUrl);
//     } else {
//       throw GithubLoginException('Could not launch $authorizationUrl');
//     }
//   }

//   static Future<Map<String, String>> _listen() async {
//     HttpServer? _redirectServer;
//     try {
//       _redirectServer = await HttpServer.bind('localhost', 0);
//       var request = await _redirectServer.first;
//       var params = request.uri.queryParameters;
//       request.response.statusCode = 200;
//       request.response.headers.set('content-type', 'text/plain');
//       request.response.writeln('Authenticated! You can close this tab.');
//       await request.response.close();
//       return params;
//     } catch (e) {
//       // print('Error during listening: $e');
//       return {};
//     } finally {
//       await _redirectServer?.close();
//     }
//   }

//   static Future<CurrentUser?> getViewerDetails(oauth2.Client? client) async {
//     try {
//       if (client != null) {
//         final gitHub = GitHub(
//             auth: Authentication.withToken(
//                 client.credentials.accessToken.toString()));
//         return gitHub.users.getCurrentUser();
//       } else {
//         throw GithubLoginException('OAuth client is null');
//       }
//     } catch (e) {
//       print('Error getting viewer details: $e');
//       return null;
//     }
//   }
// }

// class _JsonAcceptingHttpClient extends http.BaseClient {
//   final _httpClient = http.Client();
//   @override
//   Future<http.StreamedResponse> send(http.BaseRequest request) {
//     request.headers['Accept'] = 'application/json';
//     return _httpClient.send(request);
//   }
// }

// class GithubLoginException implements Exception {
//   const GithubLoginException(this.message);
//   final String message;
//   @override
//   String toString() => message;
// }
