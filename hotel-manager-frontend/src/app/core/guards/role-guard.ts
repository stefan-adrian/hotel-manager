import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {AuthenticationService} from "../services/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {


  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const expectedRole : String = route.data.expectedRole;
    const role: String = this.authenticationService.getUserRole();
    if (role !== expectedRole) {
      this.router.navigate(['login']);
      return false;
    }
    return true;

  }
}
