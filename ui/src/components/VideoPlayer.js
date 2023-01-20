import Modal from "./Modal";

export default function VideoPlayer(props) {
  return (
    <>
      <Modal
        title="Video"
        body={
          <iframe
            width="420"
            height="315"
            className="rounded-md"
            src={`https://www.youtube.com/embed/${props.videoId}`}
          ></iframe>
        }
        closeModal={props.closeModal}
      />
    </>
  );
}
