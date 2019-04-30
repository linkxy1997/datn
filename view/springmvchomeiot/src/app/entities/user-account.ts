export class UserAccount {
    username:string;
    password:string;
    authenticationToken:string;

    constructor(username:string,password:string){
        this.username=username;
        this.password=password;
    }
}
