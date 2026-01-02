use crate::{
    api::{AuthConfig, BooksApi},
    book::Book,
    opts::{BlogCommand, InvalidCommand},
};

mod api;
mod book;
mod opts;

#[tokio::main]
async fn main() {
    let auth_config = AuthConfig::load().expect("Error while loading config");
    let client = reqwest::Client::new();
    let base_url = std::env::var("BOOKS_API_BASE_URL").expect("BOOKS_API_BASE_URL is not defined");
    let api = BooksApi {
        client,
        base_url,
        auth_config,
    };
    match opts::parse_args() {
        Ok(BlogCommand::AddBook {
            author,
            title,
            status,
            tags,
        }) => {
            let book = Book {
                author: author,
                title: title,
                status: status,
                tags: tags,
            };
            if let Err(e) = api.add(book).await {
                eprintln!(
                    "There was an error while adding the book: {}",
                    e.to_string()
                );
            }
        }
        Err(InvalidCommand(msg)) => eprintln!("Invalid command: {}", msg),
    }
}
