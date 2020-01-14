export class User {
    constructor(
        public username: string,
        public authorities: string[],
        public token: string
    ) { };
}
