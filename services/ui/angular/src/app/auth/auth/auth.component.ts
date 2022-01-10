import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  templateUrl: './auth.component.html',
  styleUrls: [ './auth.component.scss' ]
})


// AuthComponent has an embedded HTTP call that
export class AuthComponent implements OnInit {

    // restoutput: populated with a string from remote (lambda) call when ngOnInit() fires
    // used as an embedded {{ restoutput | async }} call in HTML somewhere. This relies
    // on an API gateway call and Lambda function in AWS which gets setup  with the
    // securecidemo-jenkins-k8s repository
    restoutput: Observable<string>;

    // demo: url = {serverless url} goes here
    url = 'https://iqyfvyn6r4.execute-api.us-east-1.amazonaws.com/dev/microservice';

    constructor(private http: HttpClient) { }
    ngOnInit() {
        this.restoutput = this.http.get<string>(this.url);
    }
}
