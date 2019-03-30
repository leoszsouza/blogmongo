import { Moment } from 'moment';
import { IBlog } from 'app/shared/model/blog.model';

export interface IPost {
    id?: string;
    titulo?: string;
    conteudo?: string;
    dhPublicacao?: Moment;
    post?: IBlog;
    blog?: IBlog;
}

export class Post implements IPost {
    constructor(
        public id?: string,
        public titulo?: string,
        public conteudo?: string,
        public dhPublicacao?: Moment,
        public post?: IBlog,
        public blog?: IBlog
    ) {}
}
