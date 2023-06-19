import Modal from "./Modal";
import { SummarizeForm } from "./SummarizeForm";

export default function VideoPlayer(props) {
  return (
    <>
      <Modal
        title="Video"
        body={
          <>
            <iframe
              className="rounded-md w-full"
              allowfullscreen
              allowtransparency
              height={240}
              src={`https://www.youtube.com/embed/${props.videoId}`}
            ></iframe>
            <SummarizeForm videoId={props.videoId}/>
          </>
        }
        closeModal={props.closeModal}
      />
    </>
  );
}
