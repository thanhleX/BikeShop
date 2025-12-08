export interface JwtPayloadDto {
    sub: string;
    scope: string;
    iss: string;
    username: string;
    exp: number;
    iat: number;
    jti: string;
}