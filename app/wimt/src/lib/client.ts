import type {AuthRequest, AuthResponse, WastedTime} from "./domain";

export class Client {
  private static baseUrl: string = "http://45.13.59.103:7430";
  private static ausUrl: string = Client.baseUrl + "/aus/api/v1/auth";
  private static wimtUrl: string = Client.baseUrl + "/wimt/api/v1/track";

  public ready: Promise<any>;
  private token: string = "Bearer ";

  constructor(authRequest: AuthRequest) {
    this.ready = this.login(authRequest);
  }

  public async login(authRequest: AuthRequest) {
    let opt = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify(authRequest),
    };
    return this.api<AuthResponse>(Client.ausUrl, opt)
        .then((response) => (this.token += response.token))
        .catch((error) => console.log(error))
        .then(() => console.log(this.token));
  }

  public async updateData() {
    let opt = {
      method: "GET",
      headers: {
        "Access-Control-Allow-Origin": "*",
        Authorization: this.token,
      },
    };
    return this.api<WastedTime[]>(Client.wimtUrl, opt).catch((error) =>
        console.log(error)
    );
  }

  private api<T>(url: string, opt: RequestInit): Promise<T> {
    return fetch(url, opt)
        .then((response) => {
          if (!response.ok) {
            throw new Error(response.statusText);
          }
          return response.json();
        })
        .catch((error) => console.log(error));
  }
}
