// package endpoints.snunit.server

// import endpoints.{Tupler, algebra}
// import endpoints.algebra.BasicAuthentication.Credentials
// import endpoints.algebra.Documentation

// /**
//   * @group interpreters
//   */
// trait BasicAuthentication
//     extends algebra.BasicAuthentication
//     with EndpointsWithCustomErrors {

//   private[endpoints] def authenticatedRequest[U, E, H, UE, HCred, Out](
//       method: Method,
//       url: Url[U],
//       entity: RequestEntity[E],
//       headers: RequestHeaders[H],
//       requestDocs: Documentation
//   )(
//       implicit
//       tuplerUE: Tupler.Aux[U, E, UE],
//       tuplerHCred: Tupler.Aux[H, Credentials, HCred],
//       tuplerUEHCred: Tupler.Aux[UE, HCred, Out]
//   ): Request[Out] = {
//     ???
//   }

// }
