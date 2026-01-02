use reqwest::Error;

use crate::book::Book;

pub struct AuthConfig {
    pub client_id: String,
    pub client_secret: String,
}

impl AuthConfig {
    pub fn load() -> Result<AuthConfig, String> {
        // TODO: Load these from a config file.
        let client_id =
            std::env::var("BOOKS_API_CLIENT_ID").map_err(|_| "The client ID is not set")?;
        let client_secret =
            std::env::var("BOOKS_API_CLIENT_SECRET").map_err(|_| "The client secret is not set")?;
        Ok(AuthConfig {
            client_id,
            client_secret,
        })
    }
}

pub struct BooksApi {
    pub base_url: String,
    pub client: reqwest::Client,
    pub auth_config: AuthConfig,
}

impl BooksApi {
    pub async fn add(&self, book: Book) -> Result<(), Error> {
        self.client
            .post(format!("{}/api/v1/books", &self.base_url))
            .header("CF-Access-Client-Id", &self.auth_config.client_id)
            .header("CF-Access-Client-Secret", &self.auth_config.client_secret)
            .json(&book)
            .send()
            .await?
            .error_for_status()?;
        Ok(())
    }
}
