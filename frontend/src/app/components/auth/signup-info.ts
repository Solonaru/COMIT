export class SignUpInfo {
    name: string;
    surname: string;
    username: string;
    email: string;
    role: string[];
    password: string;

    constructor(name: string, surname: string, username: string, email: string, password: string) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = ['user'];
    }
}