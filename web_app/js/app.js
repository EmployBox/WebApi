import React, {Component} from 'react'
import {BrowserRouter, Route, Switch} from 'react-router-dom'

import Navigation from './components/navigation'
import Footer from './components/footer'
import PrivateRouter from './components/privateRoute'
import Auther from './components/auther'
import JobFilters from './components/jobFilters'
import JobTable from './components/jobTable'
import CompanyFilters from './components/companyFilters'
import CompanyTable from './components/companyTable'
import UserFilters from './components/userFilters'
import UserTable from './components/userTable'

import CreateJobs from './pages/createJobs'
import SignUp from './pages/signup/signup'
import LogIn from './pages/loginPage'
import IndexPage from './pages/indexPage'
import SearchPage from './pages/searchPage'
import SignUpUser from './pages/signup/signupUser'
import SignUpCompany from './pages/signup/signupCompany'
import User from './pages/profiles/user'
import Company from './pages/profiles/company'
import Job from './pages/jobPage'
import Curricula from './pages/curriculaPage'

import URI from 'urijs'
import URITemplate from 'urijs/src/URITemplate'
import HttpRequest from './components/httpRequest'
import { Option, Render } from './searchFormOptions'
import CreateCurricula from './pages/createCurricula'

const apiURI = 'http://localhost:8080'

const auther = new Auther()
const PrivateRoute = PrivateRouter(auther)

const createJobTempl = new URITemplate('/create/jobs/{url}')
const createCurriculaTempl = new URITemplate('/create/curricula/{url}')
const signUpUserTempl = new URITemplate('/signup/user/{url}')
const signUpCompanyTempl = new URITemplate('/signup/company/{url}')
const logInTempl = new URITemplate('/logIn/{url}')
const signUpTempl = new URITemplate('/signup/{urlUser}/{urlCompany}')
const accountTempl = new URITemplate('/account/{url}')
const searchTempl = new URITemplate('/search/{url}')
const companyTempl = new URITemplate('/company/{url}')
const jobTempl = new URITemplate('/job/{url}')
const curriculaTempl = new URITemplate('/curricula/{url}')

function getLink (link, hal) {
  return hal[link]['_links'].self.href
}

function loginExpand (json) {
  return logInTempl.expand({url: getLink('login', json)})
}

export default class extends Component {
  constructor (props) {
    super(props)
    this.state = {
      authenticated: false
    }
  }

  getOptions (json) {
    const UserTable1 = ({...rest}) => <UserTable {...rest} accountTempl={accountTempl} />
    const CompanyTable1 = ({...rest}) => <CompanyTable {...rest} companyTempl={companyTempl} />
    const JobTable1 = ({...rest}) => (
      <JobTable
        {...rest}
        jobTempl={jobTempl}
        accountTempl={accountTempl}
        companyTempl={companyTempl}
        authenticated={this.state.authenticated}
        createJobsURL={createJobTempl.expand({url: getLink('jobs', json)})}
      />
    )

    return {
      jobs: new Option('Jobs', 'Job\'s title', 'title', getLink('jobs', json), new Render(JobFilters, JobTable1)),
      companies: new Option('Companies', 'Company\'s name', 'name', getLink('companies', json), new Render(CompanyFilters, CompanyTable1)),
      users: new Option('Users', 'User\'s name', 'name', getLink('users', json), new Render(UserFilters, UserTable1))
    }
  }

  render () {
    return (
      <HttpRequest url={apiURI}
        onResult={json => {
          auther.setLoginUrl(loginExpand(json))
          return (
            <div>
              <BrowserRouter>
                <div>
                  <Navigation navItems={
                    this.state.authenticated
                      ? [
                        {
                          name: 'Profile',
                          link: (auther.accountType === 'USR' ? accountTempl : companyTempl)
                            .expand({ url: auther.self }) },
                        { name: 'About', link: '/about' },
                        {
                          name: 'Log out',
                          class: 'btn btn-outline-primary',
                          click: () => this.setState(oldstate => {
                            oldstate.authenticated = false
                            auther.unAuthenticate()
                            return oldstate
                          })
                        }
                      ]
                      : [
                        { name: 'About', link: '/about' },
                        { name: 'Log in', link: loginExpand(json) },
                        {
                          name: 'Sign up',
                          link: signUpTempl.expand({
                            urlUser: getLink('users', json),
                            urlCompany: getLink('companies', json)
                          }),
                          class: 'btn btn-outline-primary'
                        }
                      ]} />
                  <Switch>
                    <Route exact path='/' render={(props) =>
                      <IndexPage options={this.getOptions(json)} searchTempl={searchTempl} />}
                    />
                    <Route exact path='/signup/user/:url' render={({ history, match }) =>
                      <SignUpUser url={URI.decode(match.params.url)} ToLogin={() => history.push(loginExpand(json))} />}
                    />
                    <Route exact path='/signup/company/:url' render={({ history, match }) =>
                      <SignUpCompany url={URI.decode(match.params.url)} ToLogin={() => history.push(loginExpand(json))} />}
                    />
                    <Route exact path='/signup/:urlUser/:urlCompany' render={({ history, match }) =>
                      <SignUp signUpUser={() => history.push(signUpUserTempl.expand({ url: match.params.urlUser }))}
                        signUpCompany={() => history.push(signUpCompanyTempl.expand({ url: match.params.urlCompany }))} />}
                    />
                    <Route exact path='/login/:url' render={({ history, match }) =>
                      <LogIn url={URI.decode(match.params.url)}
                        ToLogin={(json, auth) => {
                          this.setState(oldstate => {
                            oldstate.authenticated = true
                            oldstate.self = json['_links'].self.href
                            auther.authenticate(auth, json)
                            return oldstate
                          })
                          history.push(URI.parseQuery(history.location.search).redirect || '/')
                        }} />
                    } />
                    <Route exact path='/search/:url' render={({ match }) => (
                      <SearchPage
                        uriTemplate={new URITemplate(URI.decode(match.params.url).split('?')[0] + '{?query*}')}
                        options={this.getOptions(json)}
                        match={match}
                        searchTempl={searchTempl}
                      />
                    )} />
                    <PrivateRoute exact path='/create/curricula/:url' component={(props) =>
                      <CreateCurricula curriculaTempl={curriculaTempl} {...props} />}
                    />
                    <PrivateRoute path='/account/:url' component={(props) =>
                      <User createCurriculaTempl={createCurriculaTempl} {...props} />}
                    />
                    <PrivateRoute path='/company/:url' component={Company} />
                    <PrivateRoute exact path='/create/jobs/:jobUrl' component={CreateJobs} />
                    <PrivateRoute exact path='/job/:url' component={Job} />
                    <PrivateRoute exact path='/curricula/:url' component={Curricula} />
                    <Route path='/' render={({ history }) =>
                      <center class='py-5 alert alert-danger' role='alert'>
                        <h2>Error 404.</h2>
                        <p>The requested URL {history.location.pathname} was not found on web application.</p>
                      </center>
                    } />
                  </Switch>
                  <Footer />
                </div>
              </BrowserRouter>
            </div>
          )}}
      />
    )
  }
}
