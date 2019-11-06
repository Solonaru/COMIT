export class User {
    constructor(
        private username: string,
        private authorities: string[],
        private accessToken: string,
    ) { };
}