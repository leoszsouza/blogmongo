import { IPost } from 'app/shared/model/post.model';

export interface IBlog {
    id?: string;
    usuario?: string;
    descricao?: string;
    blogs?: IPost[];
}

export class Blog implements IBlog {
    constructor(public id?: string, public usuario?: string, public descricao?: string, public blogs?: IPost[]) {}
}
