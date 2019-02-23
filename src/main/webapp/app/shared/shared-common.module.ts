import { NgModule } from '@angular/core';

import { BlogmongoSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [BlogmongoSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [BlogmongoSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class BlogmongoSharedCommonModule {}
