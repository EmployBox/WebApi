import React from 'react'
import {withRouter} from 'react-router-dom'
import ListForm from '../components/forms/listForm'
import HttpRequest from '../components/httpRequest'
import URI from 'urijs'

export default withRouter(class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      title: undefined,
      academicBackground: undefined,
      experiences: undefined,
      previousJobs: undefined,
      projects: undefined,
      body: undefined
    }
  }
  render () {
    return (
      <div class='container'>
        <br />
        <button onClick={() => console.log(this.state)}>HelloWorld</button>
        <h4 class='btn-primary bg-dark btn-lg btn-block'>Curricula</h4>
        <label>Title</label>
        <input type='text' class='form-control' value={this.state.title} onChange={event => this.setState({ title: event.target.value })} />
        <br />
        <ListForm title='Academic Background'
          submit={inputs => this.setState({academicBackground: inputs})}
          row={({institution, studyArea, degreeObtained}) => `${degreeObtained} - ${studyArea} - ${institution}`}
          inputData={[
            {
              input: 'date',
              name: 'beginDate',
              label: 'Begin Date',
              id: 'beginDateId',
              klass: 'form-control'
            },
            {
              input: 'date',
              name: 'endDate',
              label: 'End Date',
              id: 'endDateId',
              klass: 'form-control'
            },
            {
              input: 'text',
              name: 'studyArea',
              label: 'Study Area',
              id: 'studyAreaId',
              klass: 'form-control'
            },
            {
              input: 'text',
              name: 'institution',
              label: 'Institution',
              id: 'institutionId',
              klass: 'form-control'
            },
            {
              input: 'text',
              name: 'degreeObtained',
              label: 'Degree',
              id: 'degreeObtainedId',
              klass: 'form-control'
            }
          ]}
        />
        <br />
        <ListForm title='Curriculum Experiences'
          submit={inputs => this.setState({experiences: inputs})}
          row={({competence, year}) => `${competence} - ${year} years of experience`}
          inputData={[
            {
              input: 'text',
              name: 'competence',
              label: 'Competence',
              id: 'competenceId',
              klass: 'form-control'
            },
            {
              input: 'number',
              name: 'year',
              label: 'Years',
              id: 'yearId',
              klass: 'form-control'
            }
          ]}
        />
        <br />
        <ListForm title='Previuos Jobs'
          submit={inputs => this.setState({previousJobs: inputs})}
          row={({companyName, role}) => `${role} in ${companyName}`}
          inputData={[
            {
              input: 'date',
              name: 'beginDate',
              label: 'Begin Date',
              id: 'beginDateId',
              klass: 'form-control'
            },
            {
              input: 'date',
              name: 'endDate',
              label: 'End Date',
              id: 'endDateId',
              klass: 'form-control'
            },
            {
              input: 'text',
              name: 'companyName',
              label: 'Company Name',
              id: 'companyNameId',
              klass: 'form-control'
            },
            {
              input: 'text',
              name: 'workLoad',
              label: 'Work Load',
              id: 'workLoadId',
              klass: 'form-control'
            },
            {
              input: 'text',
              name: 'role',
              label: 'Role',
              id: 'roleId',
              klass: 'form-control'
            }
          ]}
        />
        <br />
        <ListForm title='Projects'
          submit={inputs => this.setState({projects: inputs})}
          row={({name}) => name}
          inputData={[
            {
              input: 'text',
              name: 'name',
              label: 'Name',
              id: 'nameId',
              klass: 'form-control'
            },
            {
              input: 'text',
              name: 'description',
              label: 'Description',
              id: 'descriptionId',
              klass: 'form-control'
            }
          ]}
        />
        <h4 class='btn-primary bg-dark btn-lg btn-block' />
        <center>
          <button type='button' class='btn btn-success btn-lg' onClick={() => {
            const body = Object.assign({accountId: this.props.accountId}, this.state)
            delete body.body
            this.setState({body: body})
          }}>
            Create
          </button>
        </center>
        {this.state.body
          ? <HttpRequest url={URI.decode(this.props.match.params.url)}
            method='POST'
            authorization={this.props.auth}
            body={this.state.body}
            afterResult={json => this.props.history.push(this.props.curriculaTempl.expand({url: json._links.self.href}))}
          /> : <div />}
      </div>
    )
  }
})
