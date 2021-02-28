export interface Profile {
    username  : String;
    email     : String;
    followers : number;
    following : number;
    comics    : number;
    likes     : number;
    followers_arr : Profile []
}