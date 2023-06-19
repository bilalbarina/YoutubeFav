import { Component, useRef } from "react";
import axios from "axios";
import config from "../config.json";
import Loading from "./Loading";

export class SummarizeForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: false,
      videoUrl: "",
      summary: "",
    };
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  getSummary = (videoUrl) => {
    return axios.post(config.API_BASE_URI + "services/summarize", {
      url: videoUrl,
    });
  };

  handleSubmit = () => {
    this.setState({ loading: true });
    const videoUrl = "https://www.youtube.com/watch?v=" + this.props.videoId;
    this.getSummary(videoUrl).then((response) => {
      this.setState({ summary: response.data.summary });
    });
  };

  render() {
    return (
      <>
        <div className="w-full flex justify-center text-center py-6 overflow-y-scroll">
        <span className="text-blue-600"></span>
          {!!this.state.summary ? (
            <div className="w-full rounded-md bg-gray-200 p-4 text-left" dangerouslySetInnerHTML={{__html: this.state.summary}}>
            </div>
          ) : !this.state.loading ? (
            <button
              onClick={this.handleSubmit}
              className="w-full py-2 px-4 text-center bg-orange-600 text-white rounded text-sm"
            >
              Summarize
            </button>
          ) : (
            <Loading />
          )}
        </div>
      </>
    );
  }
}
