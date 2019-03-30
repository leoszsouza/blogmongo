import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'region',
                loadChildren: './region/region.module#BlogmongoRegionModule'
            },
            {
                path: 'country',
                loadChildren: './country/country.module#BlogmongoCountryModule'
            },
            {
                path: 'location',
                loadChildren: './location/location.module#BlogmongoLocationModule'
            },
            {
                path: 'department',
                loadChildren: './department/department.module#BlogmongoDepartmentModule'
            },
            {
                path: 'task',
                loadChildren: './task/task.module#BlogmongoTaskModule'
            },
            {
                path: 'employee',
                loadChildren: './employee/employee.module#BlogmongoEmployeeModule'
            },
            {
                path: 'job',
                loadChildren: './job/job.module#BlogmongoJobModule'
            },
            {
                path: 'job-history',
                loadChildren: './job-history/job-history.module#BlogmongoJobHistoryModule'
            },
            {
                path: 'region',
                loadChildren: './region/region.module#BlogmongoRegionModule'
            },
            {
                path: 'country',
                loadChildren: './country/country.module#BlogmongoCountryModule'
            },
            {
                path: 'location',
                loadChildren: './location/location.module#BlogmongoLocationModule'
            },
            {
                path: 'department',
                loadChildren: './department/department.module#BlogmongoDepartmentModule'
            },
            {
                path: 'task',
                loadChildren: './task/task.module#BlogmongoTaskModule'
            },
            {
                path: 'employee',
                loadChildren: './employee/employee.module#BlogmongoEmployeeModule'
            },
            {
                path: 'job',
                loadChildren: './job/job.module#BlogmongoJobModule'
            },
            {
                path: 'job-history',
                loadChildren: './job-history/job-history.module#BlogmongoJobHistoryModule'
            },
            {
                path: 'post',
                loadChildren: './post/post.module#BlogmongoPostModule'
            },
            {
                path: 'blog',
                loadChildren: './blog/blog.module#BlogmongoBlogModule'
            },
            {
                path: 'region',
                loadChildren: './region/region.module#BlogmongoRegionModule'
            },
            {
                path: 'country',
                loadChildren: './country/country.module#BlogmongoCountryModule'
            },
            {
                path: 'location',
                loadChildren: './location/location.module#BlogmongoLocationModule'
            },
            {
                path: 'department',
                loadChildren: './department/department.module#BlogmongoDepartmentModule'
            },
            {
                path: 'task',
                loadChildren: './task/task.module#BlogmongoTaskModule'
            },
            {
                path: 'employee',
                loadChildren: './employee/employee.module#BlogmongoEmployeeModule'
            },
            {
                path: 'job',
                loadChildren: './job/job.module#BlogmongoJobModule'
            },
            {
                path: 'job-history',
                loadChildren: './job-history/job-history.module#BlogmongoJobHistoryModule'
            },
            {
                path: 'post',
                loadChildren: './post/post.module#BlogmongoPostModule'
            },
            {
                path: 'blog',
                loadChildren: './blog/blog.module#BlogmongoBlogModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BlogmongoEntityModule {}
